package com.CID.ArchiveAPP.gestiondesFiches.services.interfaces;

import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.base.data.enums.Division;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ArchiveService {

    // Création / mise à jour
    void saveArchive(Archive archive);
    void updateArchive(Archive archive);

    // Flux “cadre” -> “agent”
    void envoyerFormulaireVersAgent(ArchiveDTO dto);

    // Listes / filtres
    List<ArchiveDTO> listerParDepartement(Departement departement);
    List<ArchiveDTO> listerTout();

    // Filtrage par pôle du cadre connecté + sécurité d’accès
    List<Archive> listForCurrentCadre(Authentication authentication);
    boolean userCanSeeArchive(Long id, Authentication authentication);

    List<ArchiveDTO> listerParDivision(Division division);
}
