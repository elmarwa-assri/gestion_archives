package com.CID.ArchiveAPP.gestiondesBoite.data.repositories;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Armoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmoireRepository extends JpaRepository<Armoire, Long> {
    // Exemple : recherche d'une armoire par son nom
    Armoire findByNom(String nom);

    List<Armoire> findByNomContainingIgnoreCase(String keyword);
}
