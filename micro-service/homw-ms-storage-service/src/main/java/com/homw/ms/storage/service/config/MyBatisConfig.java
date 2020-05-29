package com.homw.ms.storage.service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({ "com.homw.ms.storage.service.dao" })
public class MyBatisConfig {
}
