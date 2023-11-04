package com.monaschinas.readr.language.domain.service;

import com.monaschinas.readr.language.domain.model.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LanguageService {
    List<Language> getAll();
    Page<Language> getAll(Pageable pageable);
    Language getById(Long languageId);
    Language create(Language language);
    Language update(Long languageId, Language language);
    ResponseEntity<?> delete(Long languageId);

}
