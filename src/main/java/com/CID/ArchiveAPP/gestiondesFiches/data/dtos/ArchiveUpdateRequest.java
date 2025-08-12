package com.CID.ArchiveAPP.gestiondesFiches.data.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ArchiveUpdateRequest {
    private String titre;
    private String code;
    private LocalDate date;
    private String departement;
    private String nature;
    private String stade;
    private String etat;
    private LocalDate dateElimination;
    private String nomFichier;
    private String client;
    private String resume;

    @NotNull @PositiveOrZero
    private Long version; // requis pour l'optimistic lock
}