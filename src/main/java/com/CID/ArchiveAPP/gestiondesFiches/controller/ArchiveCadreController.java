package com.CID.ArchiveAPP.gestiondesFiches.controller;

import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Client;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Nature;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.ArchiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cadre")
@RequiredArgsConstructor
public class ArchiveCadreController {

    private final ArchiveService archiveService;

    /** Parse LocalDate au format yyyy-MM-dd (inputs HTML type="date"). */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                } else {
                    setValue(LocalDate.parse(text)); // ISO yyyy-MM-dd
                }
            }
        });
    }

    private void fillEnums(Model model) {
        model.addAttribute("departements", Departement.values());
        model.addAttribute("natures", Nature.values());
        model.addAttribute("clients", Client.values());
    }

    /** GET : formulaire de saisie (formulaire_cadre.html) */
    @GetMapping("/formulaire")
    public String afficherFormulaireCadre(Model model) {
        if (!model.containsAttribute("archive")) {
            ArchiveDTO dto = new ArchiveDTO();
            dto.setDate(LocalDate.now());
            model.addAttribute("archive", dto);
        }
        fillEnums(model);
        return "formulaire_cadre";
    }

    /** POST : envoi du formulaire vers l’agent */
    @PostMapping("/archives/envoiFormulaire")
    public String envoyerFormulaireVersAgent(@Valid @ModelAttribute("archive") ArchiveDTO archiveDTO,
                                             BindingResult binding,
                                             RedirectAttributes ra) {
        if (binding.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.archive", binding);
            ra.addFlashAttribute("archive", archiveDTO);
            return "redirect:/cadre/formulaire";
        }

        archiveService.envoyerFormulaireVersAgent(archiveDTO);

        ra.addFlashAttribute("successMessage",
                "Fiche archive envoyée avec succès à l’agent d’archivage.");

        ArchiveDTO vide = new ArchiveDTO();
        vide.setDate(LocalDate.now());
        ra.addFlashAttribute("archive", vide);

        return "redirect:/cadre/formulaire";
    }

    /** GET : liste consultable avec filtre par département (consulter_cadre.html) */
    @GetMapping("/consulter")
    public String consulterArchivesCadre(
            @RequestParam(value = "dep", required = false) Departement dep,
            Model model
    ) {
        List<ArchiveDTO> archives = (dep == null)
                ? archiveService.listerTout()
                : archiveService.listerParDepartement(dep);

        model.addAttribute("archives", archives);
        model.addAttribute("departements", Departement.values());
        model.addAttribute("selectedDep", dep); // pré-sélection dans le <select>
        return "consulter_cadre";
    }

    /**
     * OPTIONNEL : page dédiée si tu tiens à séparer “/cadre/archives”.
     * Change le return selon le template que tu utilises réellement.
     */
    @GetMapping("/archives")
    public String listeArchivesCadre(Model model) {
        List<ArchiveDTO> archives = archiveService.listerTout();
        model.addAttribute("archives", archives);
        // Si tu as un template distinct :
        // return "liste_archives_cadre";
        // Sinon, réutilise la page de consultation :
        return "consulter_cadre";
    }
}
