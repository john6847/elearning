package com.proyectoFinal.OMSA.Jobs;

import com.proyectoFinal.OMSA.Services.AutobusServices;
import org.quartz.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Created by anyderre on 03/12/17
 */

public class AutobusJob implements Job {
    @Autowired
    private AutobusServices autobusServices;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("Executing job");
        try{
            autobusServices.modificarAutobus();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //  System.out.println("Hello World");

}

}
