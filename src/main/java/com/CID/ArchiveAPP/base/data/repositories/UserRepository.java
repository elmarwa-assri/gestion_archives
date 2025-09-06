package com.CID.ArchiveAPP.base.data.repositories;

import com.CID.ArchiveAPP.base.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // 🔑 Trouver un utilisateur par email (utile pour login et sécurité)
    Optional<User> findByEmail(String email);

    // 🔍 Recherche par mot-clé (nom, prénom ou email)
    List<User> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String nom, String prenom, String email
    );
}
