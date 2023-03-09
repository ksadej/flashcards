package com.example.flashcards.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wordlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORD_ID")
    private Long id;
    @Column(name = "WORD")
    private String word;

    @Column(name = "WORD_TRANSLATION")
    private String word_translation;
}
