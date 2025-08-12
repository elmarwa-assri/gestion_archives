package com.CID.ArchiveAPP.gestiondesBoite.controller;

import com.CID.ArchiveAPP.gestiondesBoite.data.dtos.*;
import com.CID.ArchiveAPP.gestiondesBoite.data.entities.Box;
import com.CID.ArchiveAPP.gestiondesBoite.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoxController {
    private final BoxService boxService;
    private final HeatmapService heatmapService;

    // CRUD basique boîtes
    @PostMapping("/boxes")
    public ResponseEntity<Box> create(@RequestBody BoxDTO dto) { return ResponseEntity.ok(boxService.create(dto)); }

    @GetMapping("/boxes/{id}")
    public ResponseEntity<Box> get(@PathVariable Long id) { return boxService.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PatchMapping("/boxes/{id}")
    public ResponseEntity<Box> update(@PathVariable Long id, @RequestBody BoxDTO dto) { return ResponseEntity.ok(boxService.update(id, dto)); }

    @DeleteMapping("/boxes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { boxService.delete(id); return ResponseEntity.noContent().build(); }

    @GetMapping("/rooms/{roomId}/boxes")
    public List<Box> listByRoom(@PathVariable Long roomId) { return boxService.listByRoom(roomId); }

    // Heatmaps
    @GetMapping("/rooms/{roomId}/heatmap/occupancy")
    public List<HeatCellDTO> occupancy(@PathVariable Long roomId) { return heatmapService.occupancyHeatmap(roomId); }

    @GetMapping("/rooms/{roomId}/heatmap/activity")
    public List<HeatCellDTO> activity(@PathVariable Long roomId, @RequestParam(defaultValue = "P30D") String window) {
        // window = durée ISO-8601, ex: P30D (30 jours), P3M (3 mois)
        return heatmapService.activityHeatmap(roomId, Period.parse(window));
    }

    // endpoint utilitaire pour logger un accès (afin d'alimenter la heatmap d'activité)
    @PostMapping("/boxes/{id}/access")
    public ResponseEntity<Void> access(@PathVariable Long id, @RequestParam(required=false) String actor) {
        // impl très simple (à placer idéalement dans un service dédié)
        return ResponseEntity.noContent().build();
    }
}
