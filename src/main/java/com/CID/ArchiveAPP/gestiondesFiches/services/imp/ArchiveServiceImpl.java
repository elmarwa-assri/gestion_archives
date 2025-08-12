package com.CID.ArchiveAPP.gestiondesFiches.services.imp;


import com.CID.ArchiveAPP.gestiondesFiches.exceptions.NotFoundException;
import com.CID.ArchiveAPP.gestiondesFiches.data.dtos.ArchiveUpdateRequest;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Client;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Nature;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.ArchiveRepository;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.ArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ArchiveServiceImpl implements ArchiveService {

    private final ArchiveRepository archiveRepository;

    @Override
    public List<Archive> getAllArchives() {
        return archiveRepository.findAll();
    }

    @Override
    public Archive saveArchive(Archive archive) {
        return archiveRepository.save(archive);
    }

    @Override
    @Transactional
    public Archive patchArchive(Long id, ArchiveUpdateRequest req) {
        Archive entity = archiveRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Archive not found"));



        // ----- Appliquer uniquement les champs non nuls (PATCH)
        if (req.getTitre() != null) entity.setTitre(req.getTitre());
        if (req.getCodeAffaire() != null) entity.setCodeAffaire(req.getCodeAffaire());
        if (req.getDateEdition() != null) entity.setDateEdition(req.getDateEdition());

        if (req.getDepartement() != null) {
            entity.setDepartement(parseEnum(Departement.class, req.getDepartement()));
        }
        if (req.getNature() != null) {
            entity.setNature(parseEnum(Nature.class, req.getNature()));
        }
        if (req.getEtat() != null) {
            entity.setEtat(parseEnum(Etat.class, req.getEtat()));
        }
        // NB: si `stade` est un String dans l’entité, on le passe tel quel.
        // S'il s'agit d'un enum (ex: StadeEtude), ajoute la conversion comme ci-dessus.
       //if (req.getStade() != null) entity.setStade(req.getStade());

        if (req.getDateElimination() != null) entity.setDateElimination(req.getDateElimination());
        if (req.getNomFichier() != null) entity.setNomFichier(req.getNomFichier());

        if (req.getClient() != null) {
            entity.setClient(parseEnum(Client.class, req.getClient()));
        }

        if (req.getResume() != null) entity.setResume(req.getResume());

        return archiveRepository.save(entity);
    }

    /**
     * Convertit une String en Enum en tolérant les espaces/traits d’union et la casse.
     * Lance IllegalArgumentException avec la liste des valeurs possibles si invalide.
     */
    private static <E extends Enum<E>> E parseEnum(Class<E> enumClass, String raw) {
        if (raw == null) return null;
        String normalized = raw.trim().toUpperCase()
                .replace('-', '_')
                .replace(' ', '_');
        try {
            return Enum.valueOf(enumClass, normalized);
        } catch (IllegalArgumentException ex) {
            String allowed = Stream.of(enumClass.getEnumConstants())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException(
                    "Invalid value '" + raw + "' for " + enumClass.getSimpleName() +
                            ". Allowed: [" + allowed + "]"
            );
        }
    }
}
