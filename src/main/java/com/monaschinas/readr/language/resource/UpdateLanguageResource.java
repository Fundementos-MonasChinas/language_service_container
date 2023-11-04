package com.monaschinas.readr.language.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLanguageResource {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 12)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 4)
    private String abbreviation;

}
