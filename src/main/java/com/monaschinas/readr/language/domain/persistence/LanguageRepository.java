package com.monaschinas.readr.language.domain.persistence;

import com.monaschinas.readr.language.domain.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByName(String name);
    Optional<Language> findByAbbreviation(String abbreviation);
}
