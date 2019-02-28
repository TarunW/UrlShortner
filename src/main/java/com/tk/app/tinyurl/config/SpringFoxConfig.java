package com.tk.app.tinyurl.config;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
   
	@Bean
	public Docket apiDocket() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("shortner.url"))
	            .paths(PathSelectors.ant("/v2/**"))
	            .build()
	            .apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            "TITLE",
	            "DESCIPRION",
	            "VERSION",
	            "TERMS OF SERVICE URL",
	            new Contact("NAME","http://abc.com","email@email.com"),
	            "LICENSE",
	            "http://abc.com",
	            Collections.emptyList()
	    );
	}
}