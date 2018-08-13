package com.sorbSoft.CabAcademie.Controladores;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = { "/registerCourse" }, method = RequestMethod.GET)
    public String registerCourse() {
        return "register_course";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = { "/category" }, method = RequestMethod.GET)
    public String category() {
        return "category";
    }

}
