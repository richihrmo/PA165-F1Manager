package cz.muni.fi.mvc.config;

import cz.muni.fi.TeamFormula1SampleDataConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@Configuration
@Import({TeamFormula1SampleDataConfiguration.class})
@ComponentScan(basePackages = "cz.muni.fi.mvc.controllers")
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    private static final String TEXTS = "Texts";

    /**
     * Maps the main page to a specific view.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }

    /**
     * Enables default Tomcat servlet that serves static files.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Provides localized messages.
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }

    /**
     * Provides JSR-303 Validator.
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}