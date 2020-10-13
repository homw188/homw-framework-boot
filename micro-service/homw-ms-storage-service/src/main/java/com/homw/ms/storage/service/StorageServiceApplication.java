package com.homw.ms.storage.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@MapperScan({ "com.homw.ms.storage.service.dao" })
@EnableFeignClients(basePackages = { "com.homw.ms.common.api" })
@SpringBootApplication(scanBasePackages = { "com.homw.ms.storage.service",
		"com.homw.ms.common.config" }, exclude = DataSourceAutoConfiguration.class)
public class StorageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageServiceApplication.class, args);
	}
}
