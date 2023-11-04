package com.monaschinas.readr.language.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 12)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 8)
    @Column(unique = true)
    private String abbreviation;

    // Relationships

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "language")
    private Set<BookLanguage> bookLanguages;
}
