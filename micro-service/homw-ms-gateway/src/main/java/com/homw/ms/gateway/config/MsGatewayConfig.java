package com.homw.ms.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MsGatewayConfig {

	@Bean
	public MsGatewayProperties msGatewayProperties() {
		return new MsGatewayProperties();
	}
}
