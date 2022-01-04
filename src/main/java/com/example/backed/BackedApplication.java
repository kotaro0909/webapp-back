package com.example.backed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class BackedApplication extends WebMvcConfigurerAdapter {

    public static final String APPLICATION_PROPERTIES = "/application.properties";

    private static ApplicationContext context;
    public static ApplicationContext getContext() {
        return context;
    }

    @Autowired
    private MessageSource messageSource;
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }
    @Override
    public org.springframework.validation.Validator getValidator() {
        return validator();
    }

    public static void main(String[] args) {
        context = SpringApplication.run(BackedApplication.class, args);
    }
}
