package com.sniperdev.javafleetmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Sniperdev");
        myContact.setEmail("kontakt@sniperdev.pl");

        Info information = new Info()
                .title("Fleet Manager API")
                .version("0.1")
                .description("This API exposes endpoints to manage fleet")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}