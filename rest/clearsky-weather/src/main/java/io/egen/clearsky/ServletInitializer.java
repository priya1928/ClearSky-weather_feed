package io.egen.clearsky;

import io.swagger.models.Swagger;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {	//fn to tell where are ur root application
		return new Class[] { WebConfig.class, JPAConfig.class, Swagger.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/*"};		//tells respond to only url with suffix api, that way same war file handles static content n api endpts. Also usefull wid 'nginx' later
	}
}
