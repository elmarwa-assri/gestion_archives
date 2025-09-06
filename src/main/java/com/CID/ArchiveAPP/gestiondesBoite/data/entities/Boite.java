package com.CID.ArchiveAPP.gestiondesBoite.data.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    // Chaque boîte appartient à une ligne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ligne_id", nullable = false)
    private Ligne ligne;

    // Chaque boîte appartient à une armoire
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armoire_id", nullable = false)
    private Armoire armoire;
}
