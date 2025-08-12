package com.CID.ArchiveAPP.gestiondesFiches.services.interfaces;

import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Bibliotheque;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BibliothequeService {
    Bibliotheque saveBibliotheque(Bibliotheque bibliotheque, MultipartFile file);
    List<Bibliotheque> getAllBibliotheques();
    Bibliotheque getBibliothequeById(Long id);
    Bibliotheque getBibliothequeByBiblioId(String biblioId);
}