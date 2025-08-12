package com.CID.ArchiveAPP.gestiondesFiches.data.dtos;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ArchiveRequest {
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
}
