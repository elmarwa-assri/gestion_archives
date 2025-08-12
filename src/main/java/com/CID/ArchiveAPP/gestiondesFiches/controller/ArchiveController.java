package com.CID.ArchiveAPP.gestiondesFiches.controller;

import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Client;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Nature;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.ArchiveRepository;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.ArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping // pas de préfixe: on garde les mêmes URLs
public class ArchiveController {

    private final ArchiveRepository archiveRepository;
    private final ArchiveService archiveService;

    // --- listes pour les <select> de saisie_archive.html ---
    @ModelAttribute("departements") public Departement[] departements() { return Departement.values(); }
    @ModelAttribute("natures")      public Nature[] natures()          { return Nature.values(); }
    @ModelAttribute("etats")        public Etat[] etats()              { return Etat.values(); }
    @ModelAttribute("clients")      public Client[] clients()          { return Client.values(); }

    @GetMapping("/archives/ajouter")
    public String pageSaisieArchive(Model model) {
        model.addAttribute("archive", new Archive()); // ou ArchiveDTO si tu préfères
        return "saisie_archive";
    }

    @PostMapping("/archives/enregistrer")
    public String enregistrerArchive(@ModelAttribute("archive") Archive archive,
                                     RedirectAttributes ra) {
        archiveService.saveArchive(archive);
        ra.addFlashAttribute("successMessage", "Fiche archive enregistrée avec succès.");
        return "redirect:/archives/agent";
    }

    @GetMapping("/formulaire-cadre")
    public String afficherFormulaireCadre(Model model) {
        model.addAttribute("archive", new ArchiveDTO());
        return "Formulaire_cadre";
    }

    @PostMapping("/archives/envoiFormulaire")
    public String envoyerFormulaireVersAgent(@ModelAttribute("archive") ArchiveDTO archiveDTO,
                                             RedirectAttributes ra) {
        archiveService.envoyerFormulaireVersAgent(archiveDTO);
        ra.addFlashAttribute("successMessage", "Fiche archive enregistrée avec succès.");
        return "redirect:/formulaire-cadre";
    }

    @GetMapping("/archives/agent")
    public String listeArchivesAgent(Model model) {
        List<Archive> archives = archiveRepository.findAll();
        model.addAttribute("archives", archives);
        return "liste_archive_agent";
    }
}
