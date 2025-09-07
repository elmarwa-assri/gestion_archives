// com.CID.ArchiveAPP.gestiondesFiches.controller.ArchiveDivisionController
package com.CID.ArchiveAPP.gestiondesFiches.controller;

import com.CID.ArchiveAPP.base.data.dtos.ArchiveDTO;
import com.CID.ArchiveAPP.base.data.enums.Division;
import com.CID.ArchiveAPP.gestiondesFiches.services.interfaces.ArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArchiveDivisionController {

    private final ArchiveService archiveService;

    @GetMapping("/liste_archive_division")
    public String listeArchivesDivision(
            @RequestParam(value = "div", required = false) Division division,
            Model model
    ) {
        List<ArchiveDTO> archives = (division == null)
                ? archiveService.listerTout()
                : archiveService.listerParDivision(division);

        model.addAttribute("archives", archives);
        model.addAttribute("divisions", Division.values());
        model.addAttribute("selectedDiv", division);
        return "liste_archive_division"; // => src/main/resources/templates/liste_archive_division.html
    }
}
