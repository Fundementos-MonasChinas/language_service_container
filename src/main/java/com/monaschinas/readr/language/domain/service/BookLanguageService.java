package com.monaschinas.readr.language.domain.service;

import com.monaschinas.readr.language.domain.model.BookLanguage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookLanguageService {
    List<BookLanguage> getAll();
    BookLanguage getById(Long bookLanguageId);
    BookLanguage create(BookLanguage bookLanguage);
    ResponseEntity<?> delete(Long bookLanguageId);
}
