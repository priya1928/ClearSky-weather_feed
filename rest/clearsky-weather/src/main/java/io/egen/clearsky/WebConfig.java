package io.egen.clearsky;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration				//not telling spring anything extra just telling u'll be using core features which are already there in Libraries
@EnableWebMvc				//to tell spring its gng to be a web app that will have GET,POST kinda http requests
@ComponentScan			
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("http://mocker.egen.io")
			.allowedMethods("PUT", "DELETE","POST","GET","OPTIONS")
				.allowedHeaders("header1", "header2", "header3")
			.exposedHeaders("header1", "header2");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
