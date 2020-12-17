package com.nikitin.javaSchool.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {

        return new Class[]{HibernateConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
