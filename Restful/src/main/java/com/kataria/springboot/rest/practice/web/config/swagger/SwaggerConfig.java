package com.kataria.springboot.rest.practice.web.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// Best way to document various APIs is to logically group them as shown below .
	// As mostly one controller will be present in
	// a specific package . Make multiple dockets for multiple packages i.e. each
	// for one package having one controller. It will be
	// displayed by groupName in swagger UI in top left drop down.
	// We can also ignore some controller by setting @APIOperartions(value = "some
	// Value" , hidden =true)
	// annotation or by regular or ant expression.

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.kataria.springboot.rest.practice.web")).build();
	}

	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("User Resource").select()
				.apis(RequestHandlerSelectors.basePackage("com.kataria.springboot.rest.practice.web.user")).build();

	}

	@Bean
	public Docket helloWorldApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("HelloWorld service").select()
				.apis(RequestHandlerSelectors.basePackage("com.kataria.springboot.rest.practice.web.helloworld"))
				.build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Restful Services in Spring Boot API documentation").version("1.0")
				.contact(new Contact("Vaneet", "https://github.com/vaneetkataria/Restful_Webservices_In_SpringBoot",
						"vaneetkataria54@gmail.com"))
				.build();
	}
}
