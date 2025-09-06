package com.CID.ArchiveAPP.base.services.imp;


import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Bibliotheque;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.BibliothequeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibliothequeService {

    private final BibliothequeRepository bibliothequeRepository;

    public List<Bibliotheque> getAllBiblios() {
        return bibliothequeRepository.findAll();
    }

    public Bibliotheque getById(Long id) {
        return bibliothequeRepository.findById(id).orElse(null);
    }

    public Bibliotheque getByBiblioId(String biblioId) {
        return bibliothequeRepository.findByBiblioId(biblioId);
    }

    public Bibliotheque save(Bibliotheque biblio) {
        return bibliothequeRepository.save(biblio);
    }

    public void delete(Long id) {
        bibliothequeRepository.deleteById(id);
    }
}
