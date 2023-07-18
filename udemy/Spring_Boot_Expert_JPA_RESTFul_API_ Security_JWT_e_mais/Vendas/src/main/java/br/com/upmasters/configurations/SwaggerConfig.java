package br.com.upmasters.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Api de Vendas")
        .contact(contact())
        .description("Api do Curso de Spring Boot")
        .version("V1.0").build();
  }

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors
            .basePackage("br.com.upmasters.rest.controller"))
        .paths(PathSelectors.any())
        .build()
        .securityContexts(Arrays.asList(securityContext()))
        .securitySchemes(Arrays.asList(apiKey()));

  }

  public Contact contact() {
    return new Contact(
        "Jay Benedicto",
        "https://www.jay.com.br",
        "soamazing@gmail.com"
    );
  }

  public ApiKey apiKey() {
    return new ApiKey("JWT",
        "Authorization",
        "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .operationSelector(operationContext -> true)
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global",
        "accessEverything");

    AuthorizationScope[] scopes = new AuthorizationScope[1];
    scopes[0] = authorizationScope;

    SecurityReference reference = new SecurityReference("JWT", scopes);

    List<SecurityReference> auths = new ArrayList<>();
    auths.add(reference);

    return auths;

  }

}