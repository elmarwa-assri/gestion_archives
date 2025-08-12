package com.CID.ArchiveAPP.gestiondesBoite.data.repositories;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
    List<Box> findByRoom_Id(Long roomId);
    boolean existsByRoom_IdAndRowIndexAndColIndex(Long roomId, int row, int col);
}