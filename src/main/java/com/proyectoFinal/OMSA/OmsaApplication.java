package com.proyectoFinal.OMSA;


import com.proyectoFinal.OMSA.Services.UsuarioServices;
import com.proyectoFinal.OMSA.config.SchedulerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@Import({SchedulerConfig.class})
@SpringBootApplication
public class OmsaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(OmsaApplication.class, args);

		UsuarioServices usuarioServices = (UsuarioServices)appContext.getBean("usuarioServices");
		usuarioServices.crearAdmin();
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OmsaApplication.class);
	}

}
