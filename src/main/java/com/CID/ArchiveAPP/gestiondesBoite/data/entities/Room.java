package com.CID.ArchiveAPP.gestiondesBoite.data.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String code;           // ex: "DEPOT-A-01"

    @Column(nullable=false)
    private int rowsCount;         // nombre de lignes dans la salle

    @Column(nullable=false)
    private int colsCount;         // nombre de colonnes dans la salle
}