package com.CID.ArchiveAPP.gestiondesBoite.data.repositories;



import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Ligne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneRepository extends JpaRepository<Ligne, Long> {
    // Exemple : recherche d'une ligne par son nom
    Ligne findByNom(String nom);

    List<Ligne> findByNomContainingIgnoreCase(String keyword);
}
