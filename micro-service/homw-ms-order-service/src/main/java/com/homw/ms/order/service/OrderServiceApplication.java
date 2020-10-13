package com.homw.ms.order.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@MapperScan({ "com.homw.ms.order.service.dao" })
@EnableFeignClients(basePackages = { "com.homw.ms.common.api" })
@SpringBootApplication(scanBasePackages = { "com.homw.ms.order.service",
		"com.homw.ms.common.config" }, exclude = DataSourceAutoConfiguration.class)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
