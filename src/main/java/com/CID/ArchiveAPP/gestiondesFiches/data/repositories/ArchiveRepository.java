package com.CID.ArchiveAPP.gestiondesFiches.data.repositories;

import com.CID.ArchiveAPP.base.data.enums.Division;
import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
    List<Archive> findAllByEtat(Etat etat);

    List<Archive> findAllByDepartementAndEtat(Departement departement, Etat etat);

    List<Archive> findAllByDivision(Division division);
    List<Archive> findAllByDepartement(Departement departement);

    boolean existsByIdAndDepartement(Long id, Departement departement);

    @Query("SELECT MONTH(a.date), COUNT(a) " +
            "FROM Archive a " +
            "WHERE a.date IS NOT NULL " +
            "GROUP BY MONTH(a.date) " +
            "ORDER BY MONTH(a.date)")
    List<Object[]> countArchivesByMonth();

    // ✅ Donne un @Query à la méthode attendue par ton service
    // (portable: on cherche sur des champs texte)
    @Query("SELECT a FROM Archive a " +
            "WHERE LOWER(a.titre) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(a.code)  LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(a.resume) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Archive> searchArchives(@Param("keyword") String keyword);
}
