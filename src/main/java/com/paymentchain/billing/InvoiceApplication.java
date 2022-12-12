package com.paymentchain.billing;




import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
public class InvoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceApplication.class, args);
    }

     //Define all details for app info
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Alkosto")
                .description("Actualmente la pagina de carrito es cargada en cada cambio que se hace en los items/productos: cambiar o eliminar cantidades. Este comportamiento de recarga utiliza muchos recursos de los servidores hybris debido a recarga de todos los componentes de la pagina, header, footer, mega menú, y componentes. Utilizando ajax, la pagina no se recargaría en cada acción del cliente evitando consumo no necesario de los servidores hybris.\n"
                		+ "Dadas las recomendaciones del equipo técnico mediante la HU <a href=\"https://jira.keyrus.info/browse/ALK-57839\" >ALK-56360</a>, se requiere la implementación de nuevos servicio que soporten este comportamiento.")
                .termsOfServiceUrl("https://www.alkosto.com/terminos-condiciones/sitio-web/c/sitio-web")
                //.contact(new Contact("ALK-56360", "", "https://jira.keyrus.info/browse/ALK-56360"))
                .license("License")
                .licenseUrl("https://www.alkosto.com/contacto/c/contacto")
                .version("1.0")
                .build();
    }   


//main Swagger config definition    
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                 .groupName("CartAjax-spis")
                .apiInfo(apiInfo())
                //set up JWT input
                //.securityContexts(Arrays.asList(securityContext()))
                //.securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.paymentchain"))
                .paths(PathSelectors.any())
                .build();
                //.tags(new Tag("CartAjax API", "Todos lo servicios rest que van asociados a esta funcionalidad") {});
    }

    //define API key to include the header   
    /*private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }*/
    //condigure JWT security with global Autorization Scope

    /*private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }*/

    /*private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }*/
    
}
