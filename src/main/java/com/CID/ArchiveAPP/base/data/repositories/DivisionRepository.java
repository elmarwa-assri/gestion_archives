package com.CID.ArchiveAPP.base.data.repositories;

import com.CID.ArchiveAPP.base.data.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionRepository extends JpaRepository<Division, Long> {
    List<Division> findAllByPole_Id(Long poleId);
    boolean existsByNomAndPole_Id(String nom, Long poleId);
}
