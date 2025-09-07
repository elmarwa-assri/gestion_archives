package com.CID.ArchiveAPP.gestiondesBoite.controller;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Boite;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.ArmoireRepository;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.BoiteRepository;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.LigneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoiteController {

    private final BoiteRepository boiteRepository;
    private final LigneRepository ligneRepository;
    private final ArmoireRepository armoireRepository;

    @GetMapping("/gestion-boite")
    public String listBoites(@RequestParam(value = "keyword", required = false) String keyword,
                             Model model) {

        List<Boite> boites = (keyword != null && !keyword.isBlank())
                ? boiteRepository.searchWithRelations(keyword.trim())
                : boiteRepository.findAllWithRelations();

        model.addAttribute("boites", boites);
        model.addAttribute("newBoite", new Boite());
        model.addAttribute("lignes", ligneRepository.findAll());
        model.addAttribute("armoires", armoireRepository.findAll());
        model.addAttribute("keyword", keyword);

        return "gestion_boites"; // resources/templates/gestion_boites.html
    }

    @PostMapping("/gestion-boite/ajouter")
    public String addBoite(@ModelAttribute("newBoite") Boite boite,
                           @RequestParam("ligneId") Long ligneId,
                           @RequestParam("armoireId") Long armoireId,
                           RedirectAttributes ra) {
        try {
            boite.setLigne(ligneRepository.getReferenceById(ligneId));
            boite.setArmoire(armoireRepository.getReferenceById(armoireId));
            boiteRepository.save(boite);
            ra.addFlashAttribute("successMessage", "Boîte ajoutée.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("errorMessage", "Le code de boîte existe déjà.");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erreur lors de l’ajout de la boîte.");
        }
        return "redirect:/gestion-boite";
    }

    @GetMapping("/gestion-boite/supprimer/{id}")
    public String deleteBoite(@PathVariable Long id, RedirectAttributes ra) {
        try {
            boiteRepository.deleteById(id);
            ra.addFlashAttribute("successMessage", "Boîte supprimée.");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Impossible de supprimer cette boîte.");
        }
        return "redirect:/gestion-boite";
    }
}
