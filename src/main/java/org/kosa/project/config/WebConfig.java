package org.kosa.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${image.upload-dir}")
    private String actualFileSaveDirUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/imgs/**")
                        .addResourceLocations("file:" + actualFileSaveDirUrl + "/");

        registry.addResourceHandler("/imgs/users/**")
                        .addResourceLocations("file:" + actualFileSaveDirUrl + "/users/");

        registry.addResourceHandler("/imgs/meeting/**")
                .addResourceLocations("file:" + actualFileSaveDirUrl + "/meeting/");
    }
}
