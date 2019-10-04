package exodia.web.controllers;

import exodia.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    private final DocumentService documentService;

    @Autowired
    public HomeController(DocumentService service) {
        this.documentService = service;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        if (session != null && session.getAttribute("username") != null) {
            model.addAttribute("documents", documentService.findAllShortView());
            return "home";
        } else {
            return "index";
        }
    }
}
