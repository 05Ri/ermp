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
				.title("SSAFIT 영상 관리 API")
				.description("<h3>SSAFY 관통 프로젝트 - Spring</h3>")
				.version("1.0")
				.contact(new Contact().name("SSAFY").url("http://ssafy.com").email("ssafy@ssafy.com"))
				.license(new License().name("[김예현] [김진현]"));
	}
}
