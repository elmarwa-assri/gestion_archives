package com.CID.ArchiveAPP.gestiondesFiches.services.imp;

import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
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

    // --------- NOUVEAU : filtrage par pôle (Departement) à partir des roles ----------
    @Override
    public List<Archive> listForCurrentCadre(Authentication authentication) {
        Departement dep = extractDepartementFromAuthorities(authentication.getAuthorities());
        if (dep == null) {
            // Aucune correspondance trouvée -> renvoyer vide ou tout (au choix)
            return List.of();
        }
        return archiveRepository.findAllByDepartement(dep);
    }

    // --------- NOUVEAU : sécuriser l'accès fiche détail ----------
    @Override
    public boolean userCanSeeArchive(Long id, Authentication authentication) {
        Departement dep = extractDepartementFromAuthorities(authentication.getAuthorities());
        return (dep != null) && archiveRepository.existsByIdAndDepartement(id, dep);
    }

    /**
     * Convertit un rôle en Departement.
     * Convention attendue : ROLE_EEP, ROLE_DPDPM, ROLE_SIG, etc.
     * On enlève "ROLE_" et on mappe sur l'enum Departement.
     */
    private Departement extractDepartementFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null) return null;

        for (GrantedAuthority auth : authorities) {
            String name = auth.getAuthority();
            if (name == null) continue;

            String key = name;
            if (key.startsWith("ROLE_")) {
                key = key.substring("ROLE_".length());
            }
            key = key.trim().toUpperCase();

            try {
                return Departement.valueOf(key); // ex: "EEP" -> Departement.EEP
            } catch (IllegalArgumentException ignore) {
                // pas un departement, on continue
            }
        }
        return null;
    }
}
