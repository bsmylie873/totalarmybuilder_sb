package com.coe.totalarmybuilder.controller;

import com.coe.totalarmybuilder.dto.Faction.FactionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.model.view.faction.FactionDetailView;
import com.coe.totalarmybuilder.model.view.faction.FactionView;
import com.coe.totalarmybuilder.model.view.unit.UnitView;
import com.coe.totalarmybuilder.service.FactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/factions")
@RequiredArgsConstructor
public class FactionController {

    private final FactionService factionService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FactionView>> getFactions() {
        List<FactionDto> factionList = factionService.findAll();
        return ResponseEntity.ok(mapper.map(factionList, FactionView.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FactionDetailView> getFactionById(final int id) {
        FactionDto faction = factionService.findById(id);
        return ResponseEntity.ok(mapper.map(faction, FactionDetailView.class));
    }

    @GetMapping(value = "/{id}/units/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnitView>> getFactionUnits(final int id) {
        List<UnitDto> factionUnits = factionService.findUnitsByFactionId(id);
        return ResponseEntity.ok(mapper.map(factionUnits, UnitView.class));
    }
}
