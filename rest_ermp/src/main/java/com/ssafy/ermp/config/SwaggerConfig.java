package com.ssafy.ermp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }
 
    private Info apiInfo() {
		return new Info()
				.title("ERMP Test")
				.description("<h3>Exercise Routine Managemant Project - Spring</h3>")
				.version("1.0")
				.contact(new Contact().name("ERMP").url("http://localhost:5173").email("yh980827@naver.com"))
				.license(new License().name("[김예현] [김진현]"));
	}
}
