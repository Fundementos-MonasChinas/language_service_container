package com.monaschinas.readr.language.service;

import com.monaschinas.readr.shared.exception.ResourceNotFoundException;
import com.monaschinas.readr.shared.exception.ResourceValidationException;
import com.monaschinas.readr.language.domain.model.Language;
import com.monaschinas.readr.language.domain.persistence.LanguageRepository;
import com.monaschinas.readr.language.domain.service.LanguageService;
import jakarta.validation.ConstraintViolation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LanguageServiceImpl implements LanguageService {
    private static final String ENTITY = "Language";

    private final LanguageRepository languageRepository;
    private final Validator validator;

    public LanguageServiceImpl(LanguageRepository languageRepository, Validator validator) {
        this.languageRepository = languageRepository;
        this.validator = validator;
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Page<Language> getAll(Pageable pageable) {
        return languageRepository.findAll(pageable);
    }

    @Override
    public Language getById(Long languageId) {
        return languageRepository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY,languageId));
    }

    @Override
    public Language create(Language language) {
        Set<ConstraintViolation<Language>> violations = validator.validate(language);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        if(languageRepository.findByName(language.getName()).isPresent()) {
            throw new ResourceValidationException(ENTITY, "A language with the same name already exists");
        }
        return languageRepository.save(language);
    }

    @Override
    public Language update(Long languageId, Language language) {
        Set<ConstraintViolation<Language>> violations = validator.validate(language);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Optional<Language> languageWithName = languageRepository.findByName(language.getName());

        if(languageWithName.isPresent() && !languageWithName.get().getId().equals(language.getId())) {
            throw new ResourceValidationException(ENTITY, "A language with the same name already exists");
        }

        return languageRepository.findById(languageId)
                .map(languageToUpdate -> languageRepository.save(languageToUpdate
                        .withName(language.getName())
                        .withAbbreviation(language.getAbbreviation())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,languageId));
    }

    @Override
    public ResponseEntity<?> delete(Long languageId) {
        return languageRepository.findById(languageId)
                .map(language -> {
                    languageRepository.delete(language);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,languageId));
    }
}
