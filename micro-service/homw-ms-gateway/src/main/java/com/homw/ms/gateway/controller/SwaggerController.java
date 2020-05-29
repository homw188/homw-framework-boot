package com.homw.ms.gateway.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * @description collect swagger api from backend
 * @author Hom
 * @version 1.0
 * @since 2020-05-29
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerController {

	@Autowired(required = false)
	private UiConfiguration uiConfiguration;
	@Autowired(required = false)
	private SecurityConfiguration securityConfiguration;
	@Autowired
	private SwaggerResourcesProvider swaggerResources;

	@GetMapping("")
	public Mono<ResponseEntity<?>> swaggerResources() {
		return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
	}

	@GetMapping("/configuration/security")
	public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
		return Mono.just(new ResponseEntity<>(
				Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()),
				HttpStatus.OK));
	}

	@GetMapping("/configuration/ui")
	public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
		return Mono.just(new ResponseEntity<>(
				Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
	}

}