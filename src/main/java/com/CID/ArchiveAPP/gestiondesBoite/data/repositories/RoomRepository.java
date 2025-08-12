package com.CID.ArchiveAPP.gestiondesBoite.data.repositories;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByCode(String code);
}