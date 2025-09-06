package com.CID.ArchiveAPP.base.services.imp;


import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.ArchiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final ArchiveRepository archiveRepository;

    public Map<String, Long> getArchivesCountByMonth() {
        List<Object[]> results = archiveRepository.countArchivesByMonth();

        // Tableau des mois (français)
        String[] mois = {"Jan", "Fév", "Mar", "Avr", "Mai", "Juin",
                "Juil", "Août", "Sep", "Oct", "Nov", "Déc"};

        Map<String, Long> data = new LinkedHashMap<>();
        for (Object[] row : results) {
            Integer monthNumber = (Integer) row[0]; // 1 à 12
            Long count = (Long) row[1];
            data.put(mois[monthNumber - 1], count);
        }

        return data;
    }
}
