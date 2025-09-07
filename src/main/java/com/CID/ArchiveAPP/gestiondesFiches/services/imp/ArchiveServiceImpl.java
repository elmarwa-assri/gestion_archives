package com.CID.ArchiveAPP.gestiondesFiches.services.imp;

import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.base.data.enums.Division;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.ArchiveRepository;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.ArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat.AG;

@Service
@RequiredArgsConstructor
public class ArchiveServiceImpl implements ArchiveService {

    private final ArchiveRepository archiveRepository;

    @Override
    public void saveArchive(Archive archive) {
        archive.setEtat(Etat.AG);
        archiveRepository.save(archive);
    }



    @Override
    public void envoyerFormulaireVersAgent(ArchiveDTO dto) {
        Archive archive = new Archive();
        archive.setTitre(dto.getTitre());
        archive.setCode(dto.getCode());
        archive.setDate(dto.getDate());
        archive.setDepartement(dto.getDepartement());
        archive.setNature(dto.getNature());
        archive.setDivision(dto.getDivision());
        archive.setClient(dto.getClient());
        archive.setResume(dto.getResume());
        archive.setEtat(Etat.CA);
        archiveRepository.save(archive);
    }

    @Override
    public void updateArchive(Archive archive) {
        archive.setEtat(AG);
        archiveRepository.save(archive);
    }

    /** Lister toutes les archives (DTO) */
    @Override
    public List<ArchiveDTO> listerTout() {
        return archiveRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    /** Lister par département (DTO) */
    @Override
    public List<ArchiveDTO> listerParDepartement(Departement departement) {
        return archiveRepository.findAllByDepartement(departement).stream()
                .map(this::toDto)
                .toList();
    }


    /** Lister pour le cadre connecté (Entities) via son rôle -> Departement */
    @Override
    public List<Archive> listForCurrentCadre(Authentication authentication) {
        Departement dep = extractDepartementFromAuthorities(authentication.getAuthorities());
        if (dep == null) {
            return List.of();
        }
        return archiveRepository.findAllByDepartement(dep);
    }

    /** Vérifier l'accès à une archive par le cadre connecté */
    @Override
    public boolean userCanSeeArchive(Long id, Authentication authentication) {
        Departement dep = extractDepartementFromAuthorities(authentication.getAuthorities());
        return (dep != null) && archiveRepository.existsByIdAndDepartement(id, dep);
    }

    @Override
    public List<ArchiveDTO> listerParDivision(Division division) {
        return archiveRepository.findAllByDivision(division).stream()
                .map(this::toDto)
                .toList();
    }


    // ----------------- Helpers -----------------

    private ArchiveDTO toDto(Archive a) {
        ArchiveDTO dto = new ArchiveDTO();
        dto.setTitre(a.getTitre());
        dto.setCode(a.getCode());
        dto.setDate(a.getDate()); // ou getDateEdition() si renommé
        dto.setDepartement(a.getDepartement());
        dto.setNature(a.getNature());
        dto.setClient(a.getClient());
        dto.setResume(a.getResume());
        return dto;
    }

    /**
     * Convertit un rôle en Departement.
     * Convention : ROLE_EEP, ROLE_DPDPM, ROLE_SIG, etc.
     */
    private Departement extractDepartementFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null) return null;

        for (GrantedAuthority auth : authorities) {
            String name = auth.getAuthority();
            if (name == null) continue;

            String key = name.startsWith("ROLE_") ? name.substring("ROLE_".length()) : name;
            key = key.trim().toUpperCase();

            try {
                return Departement.valueOf(key); // ex: "EEP" -> Departement.EEP
            } catch (IllegalArgumentException ignore) {
                // pas un département, on continue
            }
        }
        return null;
    }
}
