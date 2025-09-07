package com.CID.ArchiveAPP.gestiondesBoite.data.repositories;

import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Boite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoiteRepository extends JpaRepository<Boite, Long> {

    @Query("""
           select distinct b
           from Boite b
           left join fetch b.ligne
           left join fetch b.armoire
           order by b.id desc
           """)
    List<Boite> findAllWithRelations();

    @Query("""
           select distinct b
           from Boite b
           left join fetch b.ligne
           left join fetch b.armoire
           where lower(b.code) like lower(concat('%', :keyword, '%'))
           order by b.id desc
           """)
    List<Boite> searchWithRelations(@Param("keyword") String keyword);

    // utile pour d'autres contrôleurs si besoin
    List<Boite> findByCodeContainingIgnoreCase(String keyword);
    boolean existsByCodeIgnoreCase(String code);
}
