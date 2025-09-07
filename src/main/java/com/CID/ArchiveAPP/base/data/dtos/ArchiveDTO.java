package com.CID.ArchiveAPP.base.data.dtos;

import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Client;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Nature;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ArchiveDTO {
    // Informations générales
    private String titre;
    private String code;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;              // correspond à <input type="date" th:field="*{date}">

    private Departement departement;
    private String division;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateElimination;   // correspond à <input type="date" th:field="*{dateElimination}">

    // Informations sur l’archive
    private Nature nature;
    private String nomFichier;
    private Client client;
    private String cote;

    private String resume;

    // Champ ajouté récemment
    private String numBoite;             // correspond à <input type="text" th:field="*{numBoite}">
}
