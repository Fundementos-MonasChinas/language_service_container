package com.monaschinas.readr.language.service;

import com.monaschinas.readr.shared.exception.ResourceNotFoundException;
import com.monaschinas.readr.shared.exception.ResourceValidationException;
import com.monaschinas.readr.language.domain.model.BookLanguage;
import com.monaschinas.readr.language.domain.persistence.BookLanguageRepository;
import com.monaschinas.readr.language.domain.service.BookLanguageService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookLanguageServiceImpl implements BookLanguageService {
    private static final String ENTITY = "BookLanguage";

    private final BookLanguageRepository bookLanguageRepository;

    private final Validator validator;

    public BookLanguageServiceImpl(BookLanguageRepository bookLanguageRepository, Validator validator) {
        this.bookLanguageRepository = bookLanguageRepository;
        this.validator = validator;
    }

    @Override
    public List<BookLanguage> getAll() {
        return bookLanguageRepository.findAll();
    }

    @Override
    public BookLanguage getById(Long bookLanguageId) {
        return bookLanguageRepository.findById(bookLanguageId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, bookLanguageId));
    }

    @Override
    public BookLanguage create(BookLanguage bookLanguage) {
        Set<ConstraintViolation<BookLanguage>> violations = validator.validate(bookLanguage);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return bookLanguageRepository.save(bookLanguage);
    }

    @Override
    public ResponseEntity<?> delete(Long bookLanguageId) {
        return bookLanguageRepository.findById(bookLanguageId)
                .map(bookLanguage -> {
                    bookLanguageRepository.delete(bookLanguage);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, bookLanguageId));
    }
}
