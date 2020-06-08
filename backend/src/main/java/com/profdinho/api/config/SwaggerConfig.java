package com.profdinho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build()
          .apiInfo(metaData());
    }
    
    private ApiInfo metaData() {
		return new ApiInfoBuilder()
		.title("Spring Boot REST API")
		.description("\"Spring Boot REST API para usar nas aulas\"")
		.version("1.0.0")
		.license("Apache License Version 2.0")
		.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
		.build();
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/api/**").addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
		
	}
	/*
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
	    registry.addRedirectViewController("/api/configuration/ui", "/configuration/ui");
	    registry.addRedirectViewController("/api/configuration/security", "/configuration/security");
	    registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
	    registry.addRedirectViewController("/api", "/api/swagger-ui.html");
	    registry.addRedirectViewController("/api/", "/api/swagger-ui.html");
	}
	*/

}
