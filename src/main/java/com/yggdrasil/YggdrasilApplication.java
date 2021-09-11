package com.yggdrasil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class YggdrasilApplication {

	public static void main(String[] args) {
		SpringApplication.run(YggdrasilApplication.class, args);
	}

}
