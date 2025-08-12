package com.CID.ArchiveAPP.gestiondesFiches.data.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BibliothequeRequest {
    private String title;       // Titre du livre
    private String author;      // Auteur
    private String category;    // Catégorie
    private String summary;     // Résumé
    private LocalDate dateCreation; // Date de création
}
