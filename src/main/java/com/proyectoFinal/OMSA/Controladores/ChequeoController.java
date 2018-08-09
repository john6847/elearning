package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Services.ChequeoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by anyderre on 31/07/17.
 */
@Controller
@RequestMapping("/chequeo")
public class ChequeoController {
    @Autowired
    ChequeoServices chequeoServices;


}
