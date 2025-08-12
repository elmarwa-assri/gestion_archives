package com.CID.ArchiveAPP.base.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String acceuil() {
        return "acceuil"; // va chercher acceuil.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // va chercher login.html
    }
}
