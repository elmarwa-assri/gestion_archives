package com.CID.ArchiveAPP.gestiondesFiches.data.entities;

import com.CID.ArchiveAPP.gestiondesFiches.data.enums.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;           // Ajouté
    private String codeAffaire;     // Ajouté

    @Enumerated(EnumType.STRING)
    private Departement departement;

    @Enumerated(EnumType.STRING)
    private Nature nature;

    @Enumerated(EnumType.STRING)
    private StadeEtude stadeEtude;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @Column(name = "date_elimination")
    private LocalDate dateElimination;

    @Column(name = "date_edition")
    private LocalDate dateEdition;

    private String nomFichier;

    @Enumerated(EnumType.STRING)
    private Client client;

    @Column(length = 1000)
    private String resume;
}
