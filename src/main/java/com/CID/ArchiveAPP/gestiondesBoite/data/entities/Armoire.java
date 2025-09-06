package com.CID.ArchiveAPP.gestiondesBoite.data.entities;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Armoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    // Une armoire contient plusieurs boîtes
    @OneToMany(mappedBy = "armoire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Boite> boites = new ArrayList<>();
}
