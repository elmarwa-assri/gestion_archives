package com.CID.ArchiveAPP.gestiondesBoite.controller;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Boite;
import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Armoire;
import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Ligne;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.BoiteRepository;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.ArmoireRepository;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.LigneRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestion-boite")
public class BoiteController {

    private final BoiteRepository boiteRepository;
    private final ArmoireRepository armoireRepository;
    private final LigneRepository ligneRepository;

    public BoiteController(BoiteRepository boiteRepository,
                           ArmoireRepository armoireRepository,
                           LigneRepository ligneRepository) {
        this.boiteRepository = boiteRepository;
        this.armoireRepository = armoireRepository;
        this.ligneRepository = ligneRepository;
    }

    // 📌 Afficher la page de gestion
    @GetMapping
    public String listBoites(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Boite> boites;
        if (keyword != null && !keyword.isEmpty()) {
            boites = boiteRepository.findByCodeContainingIgnoreCase(keyword);
        } else {
            boites = boiteRepository.findAll();
        }

        model.addAttribute("boites", boites);
        model.addAttribute("newBoite", new Boite());
        model.addAttribute("armoires", armoireRepository.findAll());
        model.addAttribute("lignes", ligneRepository.findAll());
        model.addAttribute("keyword", keyword);

        return "gestion_boites"; // doit correspondre au fichier gestion-boite.html
    }

    // 📌 Ajouter une boîte
    @PostMapping("/ajouter")
    public String addBoite(@ModelAttribute("newBoite") Boite boite) {
        boiteRepository.save(boite);
        return "redirect:/gestion-boite";
    }

    // 📌 Supprimer une boîte
    @GetMapping("/supprimer/{id}")
    public String deleteBoite(@PathVariable Long id) {
        boiteRepository.deleteById(id);
        return "redirect:/gestion-boite";
    }
}
