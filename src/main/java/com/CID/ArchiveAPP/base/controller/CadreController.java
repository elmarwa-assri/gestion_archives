package com.CID.ArchiveAPP.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CadreController {

    @GetMapping("/dashbord_cadre")
    public String dashboard() {
        return "dashbord_cadre";
    }

    @GetMapping("/formulaire-cadre")
    public String formulaire() {
        return "Formulaire_cadre";
    }

    @GetMapping("/consulter-cadre")
    public String consulter() {
        return "consulter_cadre";
    }
}
