package com.CID.ArchiveAPP.base.data.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.CID.ArchiveAPP.base.data.enums.Role;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    private String nom;

    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    /** Relation : chaque user appartient à UNE division */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id", foreignKey = @ForeignKey(name = "fk_user_division"))
    private Division division;

    /** Dénormalisation pratique : on garde les noms dans users */
    @Column(name = "nom_pole")
    private String nomPole;

    @Column(name = "nom_division")
    private String nomDivision;

    /** Recopie auto des noms à l’insert/update */
    @PrePersist @PreUpdate
    private void syncPoleDivisionNames() {
        if (division != null) {
            this.nomDivision = division.getNom();
            if (division.getPole() != null) {
                this.nomPole = division.getPole().getNom();
            } else {
                this.nomPole = null;
            }
        } else {
            this.nomDivision = null;
            this.nomPole = null;
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
