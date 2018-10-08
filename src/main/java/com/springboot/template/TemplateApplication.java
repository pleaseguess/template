package com.springboot.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.springboot.template.mapper")
public class TemplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
		System.out.println("http://localhost:8888/swagger-ui.html");
	}
}
