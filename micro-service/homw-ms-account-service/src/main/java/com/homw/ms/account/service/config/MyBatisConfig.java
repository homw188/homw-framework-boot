package com.homw.ms.account.service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({ "com.homw.ms.account.service.dao" })
public class MyBatisConfig {
}
