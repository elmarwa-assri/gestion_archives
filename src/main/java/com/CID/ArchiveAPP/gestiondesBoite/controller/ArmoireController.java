package com.CID.ArchiveAPP.gestiondesBoite.controller;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Armoire;
import com.CID.ArchiveAPP.gestiondesBoite.data.repositories.ArmoireRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestion-armoire")
public class ArmoireController {

    private final ArmoireRepository armoireRepository;

    public ArmoireController(ArmoireRepository armoireRepository) {
        this.armoireRepository = armoireRepository;
    }

    @GetMapping
    public String listArmoires(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Armoire> armoires;
        if (keyword != null && !keyword.isEmpty()) {
            armoires = armoireRepository.findByNomContainingIgnoreCase(keyword);
        } else {
            armoires = armoireRepository.findAll();
        }

        model.addAttribute("armoires", armoires);
        model.addAttribute("newArmoire", new Armoire());
        model.addAttribute("keyword", keyword);

        return "gestion_armoires"; // page thymeleaf
    }

    @PostMapping("/ajouter")
    public String addArmoire(@ModelAttribute("newArmoire") Armoire armoire) {
        armoireRepository.save(armoire);
        return "redirect:/gestion-armoire";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteArmoire(@PathVariable Long id) {
        armoireRepository.deleteById(id);
        return "redirect:/gestion-armoire";
    }
}

