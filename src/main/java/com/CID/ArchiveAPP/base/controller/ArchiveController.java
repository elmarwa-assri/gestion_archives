package com.CID.ArchiveAPP.base.controller;



import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.base.services.imp.ArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArchiveController {

    private final ArchiveService archiveService;

    @GetMapping("/consultation-archive")
    public String listArchives(Model model) {
        List<Archive> archives = archiveService.getAllArchives();
        model.addAttribute("archives", archives);
        return "consultation_archive"; // ton fichier Thymeleaf
    }
}
