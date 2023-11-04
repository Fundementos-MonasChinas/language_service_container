package com.monaschinas.readr.language.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("languageMicroServiceMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public LanguageMapper languageMapper(){ return new LanguageMapper(); }

    @Bean
    public BookLanguageMapper bookLanguageMapper(){ return new BookLanguageMapper(); }
}
