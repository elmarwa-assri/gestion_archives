package com.CID.ArchiveAPP.gestiondesFiches.data.repositories;




import com.CID.ArchiveAPP.gestiondesFiches.data.entities.Archive;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Departement;
import com.CID.ArchiveAPP.gestiondesFiches.data.enums.Etat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchiveRepository extends JpaRepository<Archive, Long> {

    List<Archive> findAllByEtat(Etat etat);
    List<Archive> findAllByDepartementAndEtat(Departement departement, Etat etat);
}
