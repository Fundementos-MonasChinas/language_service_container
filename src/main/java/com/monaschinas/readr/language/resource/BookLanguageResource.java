package com.monaschinas.readr.language.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class BookLanguageResource {
    private Long id;
    private Long bookId;
    private LanguageResource language;
}
