package com.CID.ArchiveAPP.gestiondesBoite.controller;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Ligne;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.LigneRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestion-ligne")
public class LigneController {

    private final LigneRepository ligneRepository;

    public LigneController(LigneRepository ligneRepository) {
        this.ligneRepository = ligneRepository;
    }

    @GetMapping
    public String listLignes(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Ligne> lignes;
        if (keyword != null && !keyword.isEmpty()) {
            lignes = ligneRepository.findByNomContainingIgnoreCase(keyword);
        } else {
            lignes = ligneRepository.findAll();
        }

        model.addAttribute("lignes", lignes);
        model.addAttribute("newLigne", new Ligne());
        model.addAttribute("keyword", keyword);

        return "gestion_lignes"; // page thymeleaf
    }

    @PostMapping("/ajouter")
    public String addLigne(@ModelAttribute("newLigne") Ligne ligne) {
        ligneRepository.save(ligne);
        return "redirect:/gestion-ligne";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteLigne(@PathVariable Long id) {
        ligneRepository.deleteById(id);
        return "redirect:/gestion-ligne";
    }
}
