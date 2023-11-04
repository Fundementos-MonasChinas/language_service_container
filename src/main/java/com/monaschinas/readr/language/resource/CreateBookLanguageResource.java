package com.monaschinas.readr.language.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookLanguageResource {
    private Long bookId;
    private Long languageId;
}
