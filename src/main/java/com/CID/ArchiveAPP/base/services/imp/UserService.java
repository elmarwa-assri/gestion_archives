package com.CID.ArchiveAPP.base.services.imp;

import com.CID.ArchiveAPP.base.data.entities.User;
import com.CID.ArchiveAPP.base.data.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔹 Lister tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔹 Sauvegarder un utilisateur
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 🔹 Récupérer un utilisateur par ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // 🔹 Mettre à jour un utilisateur existant
    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setNom(updatedUser.getNom());
                    user.setPrenom(updatedUser.getPrenom());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    user.setRole(updatedUser.getRole());

                    // Ici on utilise les nouveaux champs
                    user.setNomPole(updatedUser.getNomPole());
                    user.setNomDivision(updatedUser.getNomDivision());

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID : " + id));
    }

    // 🔹 Supprimer un utilisateur
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // 🔹 Rechercher par mot-clé
    public List<User> searchByKeyword(String keyword) {
        return userRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword, keyword
        );
    }
}
