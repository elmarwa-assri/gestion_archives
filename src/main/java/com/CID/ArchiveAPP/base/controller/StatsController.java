package com.CID.ArchiveAPP.base.controller;



import com.CID.ArchiveAPP.base.services.imp.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    // Endpoint pour récupérer les archives par mois
    @GetMapping("/api/stats/archives")
    public Map<String, Long> getArchivesStats() {
        return statsService.getArchivesCountByMonth();
    }
}
