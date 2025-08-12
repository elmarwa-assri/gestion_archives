package com.CID.ArchiveAPP.gestiondesFiches.controller;


import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Bibliotheque;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Client;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Nature;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.ArchiveRepository;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.BibliothequeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AgentFichesController {

    private final ArchiveRepository archiveRepository;
    private final BibliothequeRepository bibliothequeRepository;

    // --- listes pour les <select> de saisie_archive.html ---
    @ModelAttribute("departements") public Departement[] departements() { return Departement.values(); }
    @ModelAttribute("natures") public Nature[] natures() { return Nature.values(); }
    @ModelAttribute("etats") public Etat[] etats() { return Etat.values(); }
    @ModelAttribute("clients") public Client[] clients() { return Client.values(); }

    // ============== ARCHIVES ==============
    @GetMapping("/archives/ajouter")
    public String pageSaisieArchive(Model model) {
        model.addAttribute("archive", new Archive());
        return "saisie_archive";
    }

    @PostMapping("/archives/enregistrer")
    public String enregistrerArchive(@ModelAttribute Archive archive, RedirectAttributes ra) {
        archiveRepository.save(archive);
        ra.addFlashAttribute("successMessage", "Fiche archive enregistrée avec succès.");
        return "redirect:/archives/agent";
    }

    @GetMapping("/archives/agent")
    public String listeArchivesAgent(Model model) {
        List<Archive> archives = archiveRepository.findAll();
        model.addAttribute("archives", archives);
        return "liste_archive_agent";
    }

    // ============== BIBLIOTHEQUES ==============
    @GetMapping("/bibliotheques/ajouter")
    public String pageSaisieBibliotheque(Model model) {
        model.addAttribute("bibliotheque", new Bibliotheque());
        return "saisie_biblio";
    }

    @PostMapping("/bibliotheques/enregistrer")
    public String enregistrerBibliotheque(@ModelAttribute Bibliotheque bibliotheque,
                                          @RequestParam(value = "file", required = false) MultipartFile file,
                                          RedirectAttributes ra) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadsDir = "uploads/bibliotheques";
            Path uploadPath = Paths.get(uploadsDir);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path dest = uploadPath.resolve(filename);
            file.transferTo(dest.toFile());
            bibliotheque.setFilePath("/uploads/bibliotheques/" + filename);
        }
        bibliothequeRepository.save(bibliotheque);
        ra.addFlashAttribute("successMessage", "Fiche bibliothèque enregistrée avec succès.");
        return "redirect:/bibliotheques/agent";
    }

    @GetMapping("/bibliotheques/agent")
    public String listeBibliosAgent(Model model) {
        List<Bibliotheque> biblios = bibliothequeRepository.findAll();
        model.addAttribute("biblios", biblios);
        return "liste_bibliotheque_agent";
    }
}
