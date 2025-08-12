package com.CID.ArchiveAPP.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChefController {

    @GetMapping("/dashbord_chef")
    public String dashbordChef() {
        return "dashbord_chef";
    }

    @GetMapping("/liste_archive_pole")
    public String listeArchivePole() {
        return "liste_archive_pole";
    }
}
