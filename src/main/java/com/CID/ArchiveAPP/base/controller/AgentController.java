package com.CID.ArchiveAPP.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AgentController {

    @GetMapping("/dashbord_agent_archivage")
    public String dashboard() {
        return "dashbord_agent_archivage";
    }



    @GetMapping("/saisie-biblio")
    public String saisieBiblio() {
        return "saisie_biblio";
    }

    @GetMapping("/recevoir-formulaire")
    public String recevoirFormulaire() {
        return "recevoir_formulaire";
    }

    @GetMapping("/gestion-heatmap")
    public String gestionSalle() {
        return "gestion_heatmap";
    }
}
