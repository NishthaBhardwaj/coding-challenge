package de.mobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Nishtha Bhardwaj", "www.nishtha.com", "nishtha.bhardwaj10@gmail.com ");

    private static final Set<String> DEFAULT_PRODUCERS_AND_CONSUMERS =
            Set.of("");

    /*@Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/ad/*"),)
                .apis(RequestHandlerSelectors.basePackage("de.mobile"))
                .build()
                .apiInfo(apiDetails());


    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "rest services",
                "De Mobile Code Challenge",
                "1.0",
                "Testing..",
                new Contact("Nishtha Bhardwaj" ," ","nishtha.bhardwaj10@gmail.com"),
                "API License",
                "https://www.de.mobile.com",
                Collections.emptyList());
    }
*/
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("de.mobile.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInformation());
    }

    private ApiInfo getApiInformation(){
        return new ApiInfo("Demo REST API",
                "This is a Demo API created using Spring Boot",
                "1.0",
                "API Terms of Service URL",
                new Contact("Progressive Coder", "www.progressivecoder.com", "coder.progressive@gmail.com"),
                "API License",
                "API License URL",
                Collections.emptyList()
        );
    }
}
