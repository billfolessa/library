package com.micro.cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MicoManagerLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicoManagerLibraryApplication.class, args);
	}


    @Bean
    Docket api() {
		Docket result = new Docket(DocumentationType.SWAGGER_12)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.micro.cors"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
		
		return result;
	}

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Library Spring Boot REST API Documentation")
            .description("REST APIs For Managing Books loans in a Library")
            .contact(new Contact("Georges Kemayo", "https://gkemayo.developpez.com/", "noreply.library.test@gmail.com"))
            .version("1.0")
            .build();
    }
}
