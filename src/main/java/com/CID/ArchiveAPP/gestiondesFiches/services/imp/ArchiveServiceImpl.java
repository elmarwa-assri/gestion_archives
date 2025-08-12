package com.CID.ArchiveAPP.gestiondesFiches.services.imp;


import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.ArchiveRepository;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.ArchiveService;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

