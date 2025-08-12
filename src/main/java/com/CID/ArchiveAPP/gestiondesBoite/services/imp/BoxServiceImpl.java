package com.CID.ArchiveAPP.gestiondesBoite.services.imp;

import com.CID.ArchiveAPP.gestiondesBoite.data.dtos.BoxDTO;
import com.CID.ArchiveAPP.gestiondesBoite.data.entities.*;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.*;
import com.CID.ArchiveAPP.gestiondesBoite.services.interfaces.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service @RequiredArgsConstructor @Transactional
public class BoxServiceImpl implements BoxService {
    private final RoomRepository roomRepo;
    private final BoxRepository boxRepo;

    @Override
    public Box create(BoxDTO dto) {
        return null;
    }

    @Override
    public Optional<Box> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Box update(Long id, BoxDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Box> listByRoom(Long roomId) {
        return List.of();
    }
    // ... le reste inchangé
}