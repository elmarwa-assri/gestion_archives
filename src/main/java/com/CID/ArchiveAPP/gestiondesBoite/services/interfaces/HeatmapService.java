package com.CID.ArchiveAPP.gestiondesBoite.services.interfaces;

import com.CID.ArchiveAPP.gestiondesBoite.data.dtos.HeatCellDTO;
import java.time.Period;
import java.util.List;

public interface HeatmapService {
    List<HeatCellDTO> occupancyHeatmap(Long roomId);
    List<HeatCellDTO> activityHeatmap(Long roomId, Period window);
}