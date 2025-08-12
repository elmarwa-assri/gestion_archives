package com.CID.ArchiveAPP.gestiondesBoite.services.interfaces;

import com.CID.ArchiveAPP.gestiondesBoite.data.dtos.BoxDTO;
import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Box;
import java.util.*;

public interface BoxService {
    Box create(BoxDTO dto);
    Optional<Box> get(Long id);
    Box update(Long id, BoxDTO dto);
    void delete(Long id);
    List<Box> listByRoom(Long roomId);
}