package com.homw.ms.order.service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({ "com.homw.ms.order.service.dao" })
public class MyBatisConfig {
}
