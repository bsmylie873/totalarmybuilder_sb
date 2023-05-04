package com.coe.totalarmybuilder.controller;

import com.coe.totalarmybuilder.dto.Faction.FactionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.model.view.faction.FactionView;
import com.coe.totalarmybuilder.model.view.unit.UnitDetailView;
import com.coe.totalarmybuilder.model.view.unit.UnitView;
import com.coe.totalarmybuilder.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnitView>> getUnits() {
        List<UnitDto> unitList = unitService.findAll();
        return ResponseEntity.ok(mapper.map(unitList, UnitView.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnitDetailView> getUnitById(final int id) {
        UnitDto unit = unitService.findById(id);
        return ResponseEntity.ok(mapper.map(unit, UnitDetailView.class));
    }

    @GetMapping(value = "/{id}/factions/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FactionView>> getUnitFactions(final int id) {
        List<FactionDto> unitFactions = unitService.findFactions(id);
        return ResponseEntity.ok(mapper.map(unitFactions, FactionView.class));
    }

    /*@GetMapping(value = "/lords/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnitView>> getUnitLords() {
        List<UnitDto> unitList = unitService.findLords();
        return ResponseEntity.ok(mapper.map(unitList, UnitView.class));
    }

    @GetMapping(value = "/heroes/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnitView>> getUnitHeroes() {
        List<UnitDto> unitList = unitService.findHeroes();
        return ResponseEntity.ok(mapper.map(unitList, UnitView.class));
    }*/

}
