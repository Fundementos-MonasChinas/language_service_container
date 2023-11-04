package com.monaschinas.readr.language.domain.persistence;

import com.monaschinas.readr.language.domain.model.BookLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLanguageRepository extends JpaRepository<BookLanguage, Long> {
}
