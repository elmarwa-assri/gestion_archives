package com.CID.ArchiveAPP.base.data.repositories;

import com.CID.ArchiveAPP.base.data.entities.Pole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoleRepository extends JpaRepository<Pole, Long> {
    boolean existsByNom(String nom);
}
