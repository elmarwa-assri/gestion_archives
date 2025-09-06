package com.CID.ArchiveAPP.gestiondesFiches.services.interfaces;

import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ArchiveService {

    void saveArchive(Archive archive);

    void envoyerFormulaireVersAgent(ArchiveDTO dto);

    void updateArchive(Archive archive);

    // ⬇️ pour filtrer par pôle du cadre connecté et sécuriser la fiche
    List<Archive> listForCurrentCadre(Authentication authentication);

    boolean userCanSeeArchive(Long id, Authentication authentication);
}
