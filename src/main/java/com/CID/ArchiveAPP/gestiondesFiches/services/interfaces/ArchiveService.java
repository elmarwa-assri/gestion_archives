package com.CID.ArchiveAPP.gestiondesFiches.services.interfaces;

import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.dtos.ArchiveUpdateRequest;

import java.util.List;

public interface ArchiveService {
    List<Archive> getAllArchives();
    Archive saveArchive(Archive archive);

    // NEW: mise à jour partielle avec verrou optimiste
    Archive patchArchive(Long id, ArchiveUpdateRequest request); // remplace Long par UUID si besoin
}
