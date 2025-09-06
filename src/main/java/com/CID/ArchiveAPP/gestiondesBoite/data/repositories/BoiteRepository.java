package com.CID.ArchiveAPP.gestiondesBoite.data.repositories;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Boite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoiteRepository extends JpaRepository<Boite, Long> {
    List<Boite> findByCodeContainingIgnoreCase(String code);
}
