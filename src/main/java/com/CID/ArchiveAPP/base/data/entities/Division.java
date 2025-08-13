package com.CID.ArchiveAPP.base.data.entities;

;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "division")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_division")
    private Long id;

    @Column(name = "nom_division", nullable = false, length = 150)
    private String nom;

    /** Plusieurs Divisions appartiennent à 1 Pôle */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pole", nullable = false,
            foreignKey = @ForeignKey(name = "fk_division_pole"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Pole pole;
    @OneToMany(mappedBy = "division")
    private List<User> users;
}
