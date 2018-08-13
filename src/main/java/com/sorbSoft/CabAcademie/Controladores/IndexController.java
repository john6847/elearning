package com.sorbSoft.CabAcademie.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
public class IndexController {

    @RequestMapping(value = { "/home", "/"}, method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }
}
