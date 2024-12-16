package com.voltras.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = { "com.voltras.voltrasspring", "com.voltras.demo"})
public class SpringbootVoltrasBookingServiceApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootVoltrasBookingServiceApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootVoltrasBookingServiceApplication.class, args);
		System.out.println("It works!");
	}

}
