package app.config;

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

	public static final String DEFAULT_HANDLER_PACKAGE 	= "app.controller";	//what to expose by Swagger 
	public static final String DEFAULT_API_PATH_PATTERN = "/api/.*";		//the path pattern too to expose
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .apiInfo(new ApiInfo("MVSR Books API", "", "0.0.1", "", new Contact("", "", ""), "", "", new ArrayList<VendorExtension>()))       
          .select()                                          
          .apis(RequestHandlerSelectors.basePackage(DEFAULT_HANDLER_PACKAGE))
          .paths(PathSelectors.regex(DEFAULT_API_PATH_PATTERN))                  
          .build();                                           
    }
}