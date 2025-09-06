package com.CID.ArchiveAPP.gestiondesBoite.services.imp;



import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Boite;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.BoiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoiteService {
    private final BoiteRepository boiteRepository;

    public BoiteService(BoiteRepository boiteRepository) {
        this.boiteRepository = boiteRepository;
    }

    public List<Boite> getAllBoites() {
        return boiteRepository.findAll();
    }

    public Boite saveBoite(Boite boite) {
        return boiteRepository.save(boite);
    }

    public void deleteBoite(Long id) {
        boiteRepository.deleteById(id);
    }
}
