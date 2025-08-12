package com.CID.ArchiveAPP.gestiondesFiches.services.interfaces;


import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;

public interface ArchiveService {
    void saveArchive(Archive archive);
    void envoyerFormulaireVersAgent(ArchiveDTO archiveDTO);
}

