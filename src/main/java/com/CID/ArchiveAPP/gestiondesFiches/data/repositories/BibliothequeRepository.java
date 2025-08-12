package com.CID.ArchiveAPP.gestiondesFiches.data.repositories;

import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {
    Bibliotheque findByBiblioId(String biblioId);
}