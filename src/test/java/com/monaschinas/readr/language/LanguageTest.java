package com.monaschinas.readr.language;

import com.monaschinas.readr.language.domain.model.BookLanguage;
import com.monaschinas.readr.language.domain.model.Language;
import com.monaschinas.readr.language.domain.persistence.BookLanguageRepository;
import com.monaschinas.readr.language.domain.persistence.LanguageRepository;
import com.monaschinas.readr.language.domain.service.BookLanguageService;
import com.monaschinas.readr.language.domain.service.LanguageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LanguageTest {

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private BookLanguageRepository bookLanguageRepository;

    @Mock
    private LanguageService languageServiceMock;

    @Mock
    private BookLanguageService bookLanguageServiceMock; // Inyecta el mock del BookLanguageService.

    private LanguageService languageService; // Inyectamos la implementación y no la interfaz.

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testCreateLanguage() {
        Language language = new Language(null, "English", "EN", null);
        when(languageRepository.save(any(Language.class))).thenReturn(language);

        Language createdLanguage = languageService.create(language); // Usa la implementación.
        assertEquals("English", createdLanguage.getName());
    }


    @Test
    public void testUpdateLanguage() {
        Language existingLanguage = new Language(1L, "English", "EN", null);
        when(languageRepository.findById(1L)).thenReturn(Optional.of(existingLanguage));

        Language updatedLanguage = new Language(1L, "British English", "EN", null);
        when(languageRepository.save(any(Language.class))).thenReturn(updatedLanguage);

        Language result = languageService.update(1L, updatedLanguage);
        assertEquals("British English", result.getName());
    }

    @Test
    public void testDeleteLanguage() {
        Language languageToDelete = new Language(1L, "English", "EN", null);
        when(languageRepository.findById(1L)).thenReturn(Optional.of(languageToDelete));

        languageService.delete(1L);
        verify(languageRepository).delete(languageToDelete);
    }

    @Test
    public void testCreateBookLanguage() {
        Language language = new Language(1L, "English", "EN", null);
        BookLanguage bookLanguage = new BookLanguage(null, 100L, language);

        when(bookLanguageRepository.save(any(BookLanguage.class))).thenReturn(bookLanguage);
        when(languageServiceMock.getById(1L)).thenReturn(language);

        // Ahora usa el mock de BookLanguageService para crear el BookLanguage.
        BookLanguage createdBookLanguage = bookLanguageServiceMock.create(bookLanguage);
        assertEquals(Long.valueOf(100L), createdBookLanguage.getBookId());
    }
}
