package com.homw.ms.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description swagger configuration
 * @author Hom
 * @version 1.0
 * @since 2020-05-29
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				// Try it out -> Execute -> Base path
				.pathMapping("/")
				.select()
				// Filter api source base on prefix match
				.apis(RequestHandlerSelectors.basePackage("com.homw.ms"))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("MS Rest Api Documents")
				.description("Api Details").version("0.0.1")
				.contact(new Contact("Hom", "https://github.com/homw188/homw-framework-boot", "xxx@163.com"))
				.license("Apache License, Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}

}
