package org.song.global.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    public GroupedOpenApi groupedOpenApi (){
        return GroupedOpenApi.builder().group("게시판 aip").pathsToMatch("/api/v1").build();
    }
}
