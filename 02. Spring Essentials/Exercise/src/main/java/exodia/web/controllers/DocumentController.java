package exodia.web.controllers;

import exodia.domain.models.DocumentDetailsViewModel;
import exodia.domain.models.binding.DocumentScheduleBindingModel;
import exodia.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

//TODO:  redirect method

@Controller
public class DocumentController extends BaseController {

    private final static String DOCUMENT = "document";

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ModelAttribute(DOCUMENT)
    public DocumentDetailsViewModel documentDetailsViewModel() {
        return new DocumentDetailsViewModel();
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule";
    }

    @PostMapping("schedule")
    public String schedulePost(@ModelAttribute DocumentScheduleBindingModel bindingModel,
                               @ModelAttribute(name = DOCUMENT, binding = false) DocumentDetailsViewModel document,
                               Model model) {
        return redirect(documentService
                .schedule(bindingModel)
                .map(doc -> {
                    model.addAttribute(DOCUMENT, doc);
                    return "details" + "/" + doc.getId();
                })
                .orElse("schedule"));
    }

    @GetMapping("/details" + "/{id}")
    public String details(@PathVariable String id,
                          @ModelAttribute(name = DOCUMENT, binding = false) DocumentDetailsViewModel document,
                          Model model) {
        return getDocumentView(id, document, model, "details");
    }

    @GetMapping("/print" + "/{id}")
    public String print(@PathVariable String id,
                        @ModelAttribute(name = DOCUMENT, binding = false) DocumentDetailsViewModel document,
                        Model model) {
        return getDocumentView(id, document, model, "print");
    }

    @PostMapping("/print" + "/{id}")
    public String printPost(@PathVariable String id,
                            SessionStatus sessionStatus) {
        documentService.print(id);

        sessionStatus.setComplete();

        return redirect("/");
    }

    private String getDocumentView(String id,
                                   DocumentDetailsViewModel document,
                                   Model model,
                                   String view) {

        return id.equals(document.getId()) ?
                view :
                uuid(id)
                        .flatMap(uuid -> documentService.findById(uuid, DocumentDetailsViewModel.class))
                        .map(el -> {
                            model.addAttribute("document", el);
                            return view;
                        })
                        .orElse("/");
    }
}
