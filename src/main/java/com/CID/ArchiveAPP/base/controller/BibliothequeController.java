package com.CID.ArchiveAPP.base.controller;


import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Bibliotheque;
import com.CID.ArchiveAPP.gestiondesFiches.data.repositories.BibliothequeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BibliothequeController {

    private final BibliothequeRepository bibliothequeRepository;

    @GetMapping("/consultation-biblio")
    public String listBiblios(Model model) {
        List<Bibliotheque> biblios = bibliothequeRepository.findAll();
        model.addAttribute("biblios", biblios);
        return "consultation_biblio"; // ton fichier HTML Thymeleaf
    }
}
