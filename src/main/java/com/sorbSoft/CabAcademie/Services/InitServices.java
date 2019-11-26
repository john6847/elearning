package com.sorbSoft.CabAcademie.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitServices {

    @Autowired
    private UsuarioServices usuarioServices;

    public void init() {
        usuarioServices.crearAdmin();
//        securityServices.crearUsuarioAdmin();
    }
}
