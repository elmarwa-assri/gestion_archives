package com.CID.ArchiveAPP.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // Tableau de bord
    @GetMapping("/admin-dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }

    // Gestion des rôles
    @GetMapping("/gestion-role")
    public String roles() {
        return "gestion_role";
    }



}
