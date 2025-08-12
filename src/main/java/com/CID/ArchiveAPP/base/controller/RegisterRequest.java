package com.CID.ArchiveAPP.base.controller;


import com.CID.ArchiveAPP.base.data.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
}
