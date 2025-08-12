package com.CID.ArchiveAPP.gestiondesFiches.data.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ArchiveResponse {
    private Long id; // change en UUID si besoin
    private String titre;
    private String codeAffaire;
    private LocalDate dateEdition;
    private String departement;
    private String nature;
    private String stade;
    private String etat;
    private LocalDate dateElimination;
    private String nomFichier;
    private String client;
    private String resume;
    private Long version;
}