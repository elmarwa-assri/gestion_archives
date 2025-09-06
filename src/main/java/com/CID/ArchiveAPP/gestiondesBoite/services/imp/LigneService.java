package com.CID.ArchiveAPP.gestiondesBoite.services.imp;



import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Ligne;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.LigneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneService {
    private final LigneRepository ligneRepository;

    public LigneService(LigneRepository ligneRepository) {
        this.ligneRepository = ligneRepository;
    }

    public List<Ligne> getAllLignes() {
        return ligneRepository.findAll();
    }

    public Ligne saveLigne(Ligne ligne) {
        return ligneRepository.save(ligne);
    }

    public void deleteLigne(Long id) {
        ligneRepository.deleteById(id);
    }
}
