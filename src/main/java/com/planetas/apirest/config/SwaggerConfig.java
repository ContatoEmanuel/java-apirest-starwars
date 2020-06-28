package com.planetas.apirest.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
		public Docket planetApi() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.planetas.apirest"))
					.paths(PathSelectors.regex("/api.*"))
					.build()
					.apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
				"Planetas API REST",
				"API REST para cadastro de planetas para Star Wars.",
				"1.0",
				"Terms of Service",
				new Contact("Emanuel A Macêdo", "https://www.linkedin.com/in/emanuel-a-macedo-7b709b1b1/", 
						"@contato.emanuel97@gmail.com"),
				"Apache Licence Version 2.0",
				"https://www.apache.org/licence.html", new ArrayList<VendorExtension>()
		);
		return apiInfo;
	}
}