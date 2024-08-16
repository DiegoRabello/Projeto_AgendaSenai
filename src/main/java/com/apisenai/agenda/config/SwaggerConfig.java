package com.apisenai.agenda.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.annotation.ManagedBean;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API da agenda")
                        .version("1.0")
                        .description("Documentação da API da agenda Senai, API de uma agenda fictício do curso de Full Stack do Senai da turma de 2024"));
    }

    @Bean
    public GroupedOpenApi contatoApi() {
        return GroupedOpenApi.builder()
                .group("contatos")
                .pathsToMatch("/contato/**")
                .build();
    }
    @Bean
    public GroupedOpenApi EventoApi() {
        return GroupedOpenApi.builder()
                .group("eventos")
                .pathsToMatch("/evento/**")
                .build();
    }
    @Bean
    public GroupedOpenApi ConviteApi() {
        return GroupedOpenApi.builder()
                .group("convite")
                .pathsToMatch("/convite/**")
                .build();
    }
}
