package com.homw.ms.account.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@MapperScan({ "com.homw.ms.account.service.dao" })
@EnableFeignClients(basePackages = { "com.homw.ms.common.api" })
@SpringBootApplication(scanBasePackages = { "com.homw.ms.account.service",
		"com.homw.ms.common.config" }, exclude = DataSourceAutoConfiguration.class)
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
}
