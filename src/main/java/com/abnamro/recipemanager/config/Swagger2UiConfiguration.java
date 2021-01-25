package com.abnamro.recipemanager.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class Swagger2UiConfiguration.
 */
@Configuration
@EnableSwagger2
public class Swagger2UiConfiguration {
	
	/**
	 * Api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.abnamro.recipemanager.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	/**
	 * Api info.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {
		return new ApiInfo("Recipe Manager", "Manage your favourate recipes here", "0.0.1-SNAPSHOT", "Terms of service",
				new Contact("Venu Gopal", "Recipe Manager", "venu-gopal-reddy.koppolu@capgemini.com"), "License of API",
				"API license URL", Collections.emptyList());
	}
	
}
