package com.CID.ArchiveAPP.gestiondesBoite.data.dtos;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BoxDTO {
    private Long id;
    private Long roomId;
    private int rowIndex;
    private int colIndex;
    private String reference;
}
