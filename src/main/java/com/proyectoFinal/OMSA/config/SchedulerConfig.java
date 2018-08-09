package com.proyectoFinal.OMSA.config;

import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.proyectoFinal.OMSA.AutowiringSpringBeanJobFactory;
import com.proyectoFinal.OMSA.Jobs.AutobusJob;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by anyderre on 03/12/17.
 */
@PropertySource("classpath:application.properties")
@Configuration
public class SchedulerConfig {
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext){
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, Trigger simpleJobTrigger)
        throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        factory.setTriggers(simpleJobTrigger);
        System.out.println("starting jobs....");
        return factory;
    }
    @Bean(name = "customJobTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(@Qualifier("simpleJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean ctFactory = new CronTriggerFactoryBean();
        ctFactory.setJobDetail(jobDetail);
        ctFactory.setName("sampleJobRunner");
        ctFactory.setGroup("sampleGroup");
        ctFactory.setStartDelay(0L);
        ctFactory.setCronExpression("0 00 00 * * ?");
        return ctFactory;
    }

    @Bean
    public Properties quartzProperties() throws IOException{
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public JobDetailFactoryBean simpleJobDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(AutobusJob.class);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }
}
