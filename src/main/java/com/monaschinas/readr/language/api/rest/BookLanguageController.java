package com.monaschinas.readr.language.api.rest;

import com.monaschinas.readr.language.domain.service.BookLanguageService;
import com.monaschinas.readr.language.mapping.BookLanguageMapper;
import com.monaschinas.readr.language.resource.BookLanguageResource;
import com.monaschinas.readr.language.resource.CreateBookLanguageResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/publishing/book-languages", produces = "application/json")
public class BookLanguageController {
    private final BookLanguageService bookLanguageService;
    private final BookLanguageMapper mapper;

    public BookLanguageController(BookLanguageService bookLanguageService, BookLanguageMapper mapper) {
        this.bookLanguageService = bookLanguageService;
        this.mapper = mapper;
    }


    @GetMapping
    public Page<BookLanguageResource> getAllBookLanguages(Pageable pageable) {
        return mapper.modelListPage(bookLanguageService.getAll(), pageable);
    }

    @GetMapping("{bookLanguageId}")
    public BookLanguageResource getBookLanguageById(@PathVariable Long bookLanguageId) {
        return mapper.toResource(bookLanguageService.getById(bookLanguageId));
    }

    @PostMapping
    public BookLanguageResource createBookLanguage(@RequestBody CreateBookLanguageResource resource) {
        return mapper.toResource(bookLanguageService.create(mapper.toModel(resource)));
    }

    @DeleteMapping("{bookLanguageId}")
    public ResponseEntity<?> deleteBookLanguage(@PathVariable Long bookLanguageId) {
        return bookLanguageService.delete(bookLanguageId);
    }
}
