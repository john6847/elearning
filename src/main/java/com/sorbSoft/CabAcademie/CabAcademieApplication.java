package com.sorbSoft.CabAcademie;


import com.sorbSoft.CabAcademie.Services.InitServices;
import com.sorbSoft.CabAcademie.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CabAcademieApplication{

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(CabAcademieApplication.class, args);

//		UsuarioServices usuarioServices = (UsuarioServices)appContext.getBean("usuarioServices");
//		usuarioServices.crearAdmin();

		InitServices initServices= (InitServices) appContext.getBean("initServices");
		initServices.init();
	}



}
