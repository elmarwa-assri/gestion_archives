package com.CID.ArchiveAPP.gestiondesFiches.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bibliotheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String biblioId; // Ex: BIB-2025-001

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDate dateCreation;

    private String category; // Manuel, Guide, Référence, Rapport
    private String summary;

    private String filePath; // Chemin vers le fichier uploadé

    @CreationTimestamp
    private LocalDateTime createdAt;
}