package com.CID.ArchiveAPP.base.controller;

import com.CID.ArchiveAPP.base.data.entities.User;
import com.CID.ArchiveAPP.base.data.enums.Role;
import com.CID.ArchiveAPP.base.data.enums.Pole;
import com.CID.ArchiveAPP.base.data.enums.Division;
import com.CID.ArchiveAPP.base.services.imp.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestion-users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Afficher la liste des utilisateurs
    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", Role.values());
        model.addAttribute("poles", Pole.values());
        model.addAttribute("divisions", Division.values());

        return "gestion_users"; // ✅ ton fichier HTML
    }

    // ✅ Ajouter un utilisateur
    @PostMapping
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/gestion-users"; // ✅ redirection vers la liste
    }

    // ✅ Formulaire d’édition
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID : " + id));

        model.addAttribute("newUser", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("poles", Pole.values());
        model.addAttribute("divisions", Division.values());

        return "edit_user"; // ✅ ta page de modification
    }

    // ✅ Mise à jour d’un utilisateur
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id,
                             @ModelAttribute("newUser") User updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/gestion-users";// ✅ redirection
    }

    // ✅ Supprimer un utilisateur
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/gestion-users";// ✅ redirection
    }

    // ✅ Rechercher un utilisateur
    @GetMapping("/search")
    public String searchUsers(@RequestParam("keyword") String keyword, Model model) {
        List<User> users = userService.searchByKeyword(keyword);

        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", Role.values());
        model.addAttribute("poles", Pole.values());
        model.addAttribute("divisions", Division.values());

        return "gestion_users";
    }
}
