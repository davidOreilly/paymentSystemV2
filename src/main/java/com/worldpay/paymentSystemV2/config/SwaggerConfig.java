package com.worldpay.paymentSystemV2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/submitPayment.*"), regex("/api/submitPayment.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("nbnb")
                .description("vbnvbns")
                .termsOfServiceUrl("htvbnvbnom")
                .contact("javainbnvbn.com").license("Javvnvbse")
                .licenseUrl("javvnvb.com").version("1.0").build();
    }
}
