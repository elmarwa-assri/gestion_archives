package com.CID.ArchiveAPP.gestiondesBoite.data.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"room_id","row_index","col_index"}))
public class Box {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    private Room room;

    @Column(name="row_index", nullable=false)
    private int rowIndex; // 0..rowsCount-1

    @Column(name="col_index", nullable=false)
    private int colIndex; // 0..colsCount-1

    @Column(nullable=false)
    private String reference; // code interne de la boîte

    private LocalDate archivedAt; // date d'archivage
}
