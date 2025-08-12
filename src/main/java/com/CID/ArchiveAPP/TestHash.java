package com.CID.ArchiveAPP;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestHash {
    public static void main(String[] args) {
        String hash = new BCryptPasswordEncoder().encode("test123");
        System.out.println("Mot de passe hashé : " + hash);
    }
}
