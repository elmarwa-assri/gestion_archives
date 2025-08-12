package com.CID.ArchiveAPP.gestiondesBoite.data.repositories;
import com.CID.ArchiveAPP.gestiondesBoite.data.entities.BoxAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface BoxAccessLogRepository extends JpaRepository<BoxAccessLog, Long> {
    @Query("select l.box.id as boxId, count(l) as c " +
            "from BoxAccessLog l " +
            "where l.box.room.id = :roomId and l.accessedAt >= :since " +
            "group by l.box.id")
    List<Map<String,Object>> countByRoomSince(Long roomId, OffsetDateTime since);
}