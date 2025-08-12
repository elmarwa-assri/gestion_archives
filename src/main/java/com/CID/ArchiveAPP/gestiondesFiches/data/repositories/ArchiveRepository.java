package com.CID.ArchiveAPP.gestiondesFiches.data.repositories;


import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<Archive, Long> {
}
