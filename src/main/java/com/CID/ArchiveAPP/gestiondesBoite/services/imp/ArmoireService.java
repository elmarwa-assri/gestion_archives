package com.CID.ArchiveAPP.gestiondesBoite.services.imp;



import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Armoire;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.ArmoireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmoireService {
    private final ArmoireRepository armoireRepository;

    public ArmoireService(ArmoireRepository armoireRepository) {
        this.armoireRepository = armoireRepository;
    }

    public List<Armoire> getAllArmoires() {
        return armoireRepository.findAll();
    }

    public Armoire saveArmoire(Armoire armoire) {
        return armoireRepository.save(armoire);
    }

    public void deleteArmoire(Long id) {
        armoireRepository.deleteById(id);
    }
}
