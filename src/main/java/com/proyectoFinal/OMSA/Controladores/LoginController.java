package com.proyectoFinal.OMSA.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Usuario o contrase&ntilde;a incorrecto.");
        }

        if (logout != null) {
            model.addObject("msg", "Session cerrada exitosamente.");
        }
        model.setViewName("login");

        return model;

    }

}
