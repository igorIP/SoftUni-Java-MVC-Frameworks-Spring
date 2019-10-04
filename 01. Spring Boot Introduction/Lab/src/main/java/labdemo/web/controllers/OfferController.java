package labdemo.web.controllers;

import labdemo.domain.models.binding.OfferFindBindingModel;
import labdemo.domain.models.binding.OfferRegisterBindingModel;
import labdemo.domain.models.view.OfferViewModel;
import labdemo.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OfferController {

    private final OfferService service;

    public OfferController(OfferService service) {
        this.service = service;
    }

    //returns VIEW obj
    private static ModelAndView view(String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    //redirects to view
    private static ModelAndView redirect(String viewName) {
        return view("redirect:" + viewName);
    }

    //MAPPINGS

    @GetMapping("/register")
    public ModelAndView register() {
        return view("register.html");
    }


    @PostMapping("/register")
    public ModelAndView registerPost(OfferRegisterBindingModel model) {
        if (service.registerOffer(model)) {
            return redirect("/");
        }
        return redirect("/register");
    }

    @GetMapping("/find")
    public ModelAndView find() {
        return view("find");
    }

    @PostMapping("/find")
    public ModelAndView findPost(OfferFindBindingModel model) {
        if (service.findOffer(model, OfferViewModel.class).isPresent()) {
            return redirect("/");
        }
        return redirect("/find");
    }
}
