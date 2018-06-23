package com.noriental;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@MapperScan("com.noriental.module.*.dao") //扫描mapper
@ServletComponentScan //扫描过滤器
public class BeaseCenterApplication {
	//log
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	//properties path
	static String path = "/Users/colby/dev/workspace/bease-center/src/main/resources/config.properties";

	String key = "";

	//静态块,用于加载外问配置文件
	static {

		try {
			Properties properties = new Properties();
			// 使用InPutStream流读取properties文件
			 BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			 properties.load(bufferedReader);
			 // 获取key对应的value值
			for(Map.Entry<Object, Object> entry:properties.entrySet())
			{
				System.setProperty(entry.getKey().toString(),entry.getValue().toString());
				//LOGGER.info("===========key={},value={}==========",entry.getKey().toString(),entry.getValue().toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {


		LOGGER.info("===========BeaseCenterApplication start==========");

		SpringApplication.run(BeaseCenterApplication.class, args);

		LOGGER.info("===========key=key,value={}==========",System.getProperty("key"));
		LOGGER.info("===========BeaseCenterApplication started==========");
	}
}
