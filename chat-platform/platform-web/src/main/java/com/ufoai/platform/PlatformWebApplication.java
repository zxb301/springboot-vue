package com.ufoai.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages="com.ufoai.platform")
@EnableTransactionManagement
public class PlatformWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformWebApplication.class, args);
	}

}
