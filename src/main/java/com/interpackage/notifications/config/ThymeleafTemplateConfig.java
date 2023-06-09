/**
 * Este archivo es para configuraciones
 */
package com.interpackage.notifications.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafTemplateConfig {

    /**
     * Returns a ClassLoaderTemplateResolver object used to configure the
     * settings for resolving templates from the classpath.
     * @return a ClassLoaderTemplateResolver object with the specified settings.
     */
    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver resolver
                = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(1);
        resolver.setCheckExistence(true);
        return resolver;
    }
}
