package com.CID.ArchiveAPP.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin-dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/gestion-users")
    public String users() {
        return "gestion_users";
    }

    @GetMapping("/gestion-role")
    public String roles() {
        return "gestion_role";
    }

    @GetMapping("/consultation-archive")
    public String archives() {
        return "consultation_archive";
    }

    @GetMapping("/consultation-biblio")
    public String biblio() {
        return "consultation_biblio";
    }
}
