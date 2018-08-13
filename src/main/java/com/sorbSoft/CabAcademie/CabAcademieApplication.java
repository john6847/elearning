package com.sorbSoft.CabAcademie;


import com.sorbSoft.CabAcademie.Services.UsuarioServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CabAcademieApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(CabAcademieApplication.class, args);

		UsuarioServices usuarioServices = (UsuarioServices)appContext.getBean("usuarioServices");
		usuarioServices.crearAdmin();
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CabAcademieApplication.class);
	}

}
