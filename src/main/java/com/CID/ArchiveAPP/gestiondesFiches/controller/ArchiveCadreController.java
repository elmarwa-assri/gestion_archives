package com.CID.ArchiveAPP.gestiondesFiches.controller;

import com.CID.ArchiveAPP.gestiondesFiches.dto.ArchiveDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadre/archives") // <-- base path CADRE
public class ArchiveCadreController {

    @GetMapping("/formulaire")
    public String afficherFormulaire(Model model) {
        if (!model.containsAttribute("archive")) {
            model.addAttribute("archive", new ArchiveDTO());
        }
        return "cadre/archives/formulaire"; // adapte au nom réel de ta vue
    }

    @PostMapping("/envoiFormulaire") // <-- /cadre/archives/envoiFormulaire
    public String envoyerFormulaireVersAgent(@Valid ArchiveDTO archive,
                                             BindingResult bindingResult,
                                             RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.archive", bindingResult);
            ra.addFlashAttribute("archive", archive);
            ra.addFlashAttribute("errorMessage", "Veuillez corriger les erreurs du formulaire.");
            return "redirect:/cadre/archives/formulaire";
        }

        // TODO: ta logique métier ici (enregistrer, notifier, etc.)

        ra.addFlashAttribute("successMessage", "Formulaire envoyé par le CADRE au service agent.");
        return "redirect:/cadre/archives/formulaire";
    }
}
