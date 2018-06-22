package com.noriental;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
@MapperScan("com.noriental.module.*.dao") //扫描mapper
@ServletComponentScan //扫描过滤器
public class BeaseCenterApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {
		LOGGER.info("===========BeaseCenterApplication start==========");

		SpringApplication.run(BeaseCenterApplication.class, args);

		LOGGER.info("===========BeaseCenterApplication started==========");
	}
}
