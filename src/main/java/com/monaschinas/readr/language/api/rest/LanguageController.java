package com.monaschinas.readr.language.api.rest;

import com.monaschinas.readr.language.domain.service.LanguageService;
import com.monaschinas.readr.language.mapping.LanguageMapper;
import com.monaschinas.readr.language.resource.CreateLanguageResource;
import com.monaschinas.readr.language.resource.LanguageResource;
import com.monaschinas.readr.language.resource.UpdateLanguageResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/publishing/languages", produces = "application/json")
public class LanguageController {
  private final LanguageService languageService;
  private final LanguageMapper mapper;

  public LanguageController(LanguageService languageService, LanguageMapper mapper) {
    this.languageService = languageService;
    this.mapper = mapper;
  }

  @GetMapping
  public Page<LanguageResource> getAllLanguages(Pageable pageable){
    return mapper.modelListPage(languageService.getAll(), pageable);
  }

  @GetMapping("languageId")
  public LanguageResource getLanguageById(@PathVariable Long languageId){
    return mapper.toResource(languageService.getById(languageId));
  }

  @PostMapping
  public LanguageResource createLanguage(@RequestBody CreateLanguageResource resource){
    return mapper.toResource(languageService.create(mapper.toModel(resource)));
  }

  @PutMapping("languageId")
  public LanguageResource updateLanguage(@PathVariable Long languageId, @RequestBody UpdateLanguageResource resource){
    return mapper.toResource(languageService.update(languageId,mapper.toModel(resource)));
  }

  @DeleteMapping("languageId")
  public ResponseEntity<?> deleteLanguage(@PathVariable Long languageId){ return languageService.delete(languageId); }
}
