package labdemo.web.controllers;

import labdemo.domain.models.view.OfferViewModel;
import labdemo.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final OfferService service;

    public HomeController(OfferService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("offers", service.findAll(OfferViewModel.class));
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
}