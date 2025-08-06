package org.song.global.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "게사판 API", license = @License(name = "MIT"), contact = @Contact(email = "songjaese@gmail.com")))
public class SwaggerConfig {

    public GroupedOpenApi groupedOpenApi (){
        return GroupedOpenApi.builder().group("게시판 aip").pathsToMatch("/api/v1").build();
    }
}
