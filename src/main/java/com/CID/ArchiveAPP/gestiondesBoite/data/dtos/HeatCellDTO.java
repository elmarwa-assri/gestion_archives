package com.CID.ArchiveAPP.gestiondesBoite.data.dtos;


import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HeatCellDTO {
    private int rowIndex;
    private int colIndex;
    private long value; // charge (occupation) ou activité
}