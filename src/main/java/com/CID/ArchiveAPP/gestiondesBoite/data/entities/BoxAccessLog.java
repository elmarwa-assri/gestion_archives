package com.CID.ArchiveAPP.gestiondesBoite.data.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BoxAccessLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    private Box box;

    @Column(nullable=false)
    private OffsetDateTime accessedAt;

    private String actor; // qui a consulté (optionnel)
}
