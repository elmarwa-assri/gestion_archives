package com.CID.ArchiveAPP.gestiondesFiches.services.imp;

import com.CID.ArchiveAPP.base.services.imp.FileService;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Bibliotheque;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.BibliothequeRepository;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.BibliothequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.CID.ArchiveAPP.base.common.Constants.UPLOAD_DIR;

@Service
@RequiredArgsConstructor
public class BibliothequeServiceImpl implements BibliothequeService {

    private final BibliothequeRepository bibliothequeRepository;
    private final FileService fileService;

    @Override
    public Bibliotheque saveBibliotheque(Bibliotheque bibliotheque, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // Créer le répertoire s'il n'existe pas
                fileService.uploadFile(bibliotheque, file);
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de l'enregistrement du fichier", e);
            }
        }
        return bibliothequeRepository.save(bibliotheque);
    }

    @Override
    public List<Bibliotheque> getAllBibliotheques() {
        return bibliothequeRepository.findAll();
    }

    @Override
    public Bibliotheque getBibliothequeById(Long id) {
        return bibliothequeRepository.findById(id).orElse(null);
    }

    @Override
    public Bibliotheque getBibliothequeByBiblioId(String biblioId) {
        return bibliothequeRepository.findByBiblioId(biblioId);
    }
}