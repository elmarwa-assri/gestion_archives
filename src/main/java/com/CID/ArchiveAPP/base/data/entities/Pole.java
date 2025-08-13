package com.CID.ArchiveAPP.base.data.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pole")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pole")
    private Long id;

    @Column(name = "nom_pole", nullable = false, unique = true, length = 150)
    private String nom;

    /** 1 Pôle contient plusieurs Divisions */
    @OneToMany(mappedBy = "pole", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Division> divisions = new ArrayList<>();

    // Helpers pour tenir les deux côtés de l'association en cohérence
    public void addDivision(Division d) {
        divisions.add(d);
        d.setPole(this);
    }

}
