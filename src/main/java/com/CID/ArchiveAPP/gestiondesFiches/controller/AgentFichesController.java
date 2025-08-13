package com.CID.ArchiveAPP.gestiondesFiches.controller;


import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.base.services.imp.FileService;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Bibliotheque;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Client;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Nature;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.ArchiveRepository;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.BibliothequeRepository;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.ArchiveService;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.BibliothequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat.AG;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AgentFichesController {

    private final ArchiveRepository archiveRepository;
    private final BibliothequeRepository bibliothequeRepository;
    private final BibliothequeService bibliothequeService;
    private final FileService fileService;
    private final ArchiveService archiveService;

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
    public String enregistrerArchive(@ModelAttribute("archive")  Archive archive, RedirectAttributes ra) {
        archiveService.saveArchive(archive);
        ra.addFlashAttribute("successMessage", "Fiche archive enregistrée avec succès.");
        return "redirect:/archives/agent";
    }

    @GetMapping("/formulaire-cadre")
    public String afficherFormulaireCadre(Model model) {
        if (!model.containsAttribute("archive")) {
            ArchiveDTO archiveVide = new ArchiveDTO();
            model.addAttribute("archive", archiveVide);
        }
        return "Formulaire_cadre";
    }

    @PostMapping("/archives/envoiFormulaire")
    public String envoyerFormulaireVersAgent(@ModelAttribute("archive") ArchiveDTO archiveDTO,
                                             RedirectAttributes ra) {
        archiveService.envoyerFormulaireVersAgent(archiveDTO);

        // message de succès
        ra.addFlashAttribute("successMessage", "Fiche archive envoyée avec succès à l’agent d’archivage.");

        // formulaire vidé (avec date par défaut)
        ArchiveDTO archiveVide = new ArchiveDTO();
        ra.addFlashAttribute("archive", archiveVide);

        // redirection vers le GET
        return "redirect:/formulaire-cadre";
    }

    @GetMapping("/consulter-cadre")
    public String listeArchivesCadre(Model model) {
        List<Archive> archives = archiveRepository.findAllByEtat(Etat.CA);
        model.addAttribute("archives", archives);
        return "consulter_cadre";
    }


    @GetMapping("/archives/agent")
    public String listeArchivesAgent(Model model) {
        List<Archive> archives = archiveRepository.findAllByEtat(AG);
        model.addAttribute("archives", archives);
        return "liste_archive_agent";
    }

    @GetMapping("/recevoir-formulaire")
    public String recevoirFormulaires(Model model) {
        model.addAttribute("archives", archiveRepository.findAllByEtat(Etat.CA));
        return "recevoir_formulaire";
    }

    // Formulaire "Saisir" pré-rempli
    @GetMapping("/archives/saisir/{id}")
    public String saisirArchive(@PathVariable Long id, Model model) {
        Archive archive = archiveRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Archive introuvable avec id: " + id));

        model.addAttribute("archive", archive);
        model.addAttribute("departements", Departement.values());
        model.addAttribute("natures", Nature.values());
        model.addAttribute("clients", Client.values());

        return "saisir_archive"; // Template formulaire
    }

    @PostMapping("/archives/enregistrer-updated")
    public String updateArchive(@ModelAttribute Archive archive, RedirectAttributes ra) {
        archiveService.updateArchive(archive);
        ra.addFlashAttribute("successMessage", "Fiche archive enregistrée avec succès.");
        return "redirect:/recevoir_formulaire";
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
        bibliothequeService.saveBibliotheque(bibliotheque, file);
        ra.addFlashAttribute("successMessage", "Fiche bibliothèque enregistrée avec succès.");
        return "redirect:/bibliotheques/agent";
    }

    @GetMapping("/bibliotheques/download/{name:.+}")
    public ResponseEntity<Resource> downloadBibliotheque(@PathVariable String name) {
        Resource resource = fileService.download(name);

        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(Path.of(resource.getFile().getAbsolutePath()));
        } catch (Exception ignored) {}

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @GetMapping("/bibliotheques/agent")
    public String listeBibliosAgent(Model model) {
        List<Bibliotheque> biblios = bibliothequeRepository.findAll();
        model.addAttribute("biblios", biblios);
        return "liste_bibliotheque_agent";
    }
}
