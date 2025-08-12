package com.CID.ArchiveAPP.gestiondesBoite.services.imp;

import com.CID.ArchiveAPP.gestiondesBoite.data.dtos.HeatCellDTO;
import com.CID.ArchiveAPP.gestiondesBoite.data.entities.*;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.*;
import com.CID.ArchiveAPP.gestiondesBoite.services.interfaces.HeatmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional(readOnly = true)
public class HeatmapServiceImpl implements HeatmapService {
    private final RoomRepository roomRepo;
    private final BoxRepository boxRepo;
    private final BoxAccessLogRepository logRepo;

    @Override
    public List<HeatCellDTO> occupancyHeatmap(Long roomId) {
        return List.of();
    }

    @Override
    public List<HeatCellDTO> activityHeatmap(Long roomId, Period window) {
        return List.of();
    }
    // ... le reste inchangé
}