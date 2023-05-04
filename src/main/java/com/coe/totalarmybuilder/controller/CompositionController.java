package com.coe.totalarmybuilder.controller;

import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.dto.Composition.CreateCompositionDto;
import com.coe.totalarmybuilder.dto.Composition.UpdateCompositionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.model.view.composition.CompositionDetailView;
import com.coe.totalarmybuilder.model.view.composition.CompositionView;
import com.coe.totalarmybuilder.model.view.composition.CreateCompositionView;
import com.coe.totalarmybuilder.model.view.composition.UpdateCompositionView;
import com.coe.totalarmybuilder.model.view.unit.UnitView;
import com.coe.totalarmybuilder.service.CompositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compositions")
@RequiredArgsConstructor
public class CompositionController {

    private final CompositionService compositionService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompositionView>> getCompositions() {
        List<CompositionDto> compositionList = compositionService.findAll();
        return ResponseEntity.ok(mapper.map(compositionList, CompositionView.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompositionDetailView> getCompositionById(final int id) {
        CompositionDto composition = compositionService.findById(id);
        return ResponseEntity.ok(mapper.map(composition, CompositionDetailView.class));
    }

    @GetMapping(value = "/{id}/units/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnitView>> getCompositionUnits(final int id) {
        List<UnitDto> compositionUnits = compositionService.findUnitsByComposition(id);
        return ResponseEntity.ok(mapper.map(compositionUnits, UnitView.class));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createComposition(@RequestBody final CreateCompositionView createCompositionView) {
        final CreateCompositionDto createCompositionDto = mapper.map(createCompositionView, CreateCompositionDto.class);
        final CompositionDto compositionDto = compositionService.createComposition(createCompositionDto);
        final CompositionView compositionView = mapper.map(compositionDto, CompositionView.class);
        return new ResponseEntity(compositionView, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> updateComposition(final int id,
                                                        @RequestBody final UpdateCompositionView updateCompositionView) {
        final UpdateCompositionDto updateCompositionDto = mapper.map(updateCompositionView, UpdateCompositionDto.class);
        final Optional<CompositionDto> compositionDto = compositionService.updateComposition(id, updateCompositionDto);
        final CompositionView compositionView = mapper.map(compositionDto, CompositionView.class);
        return new ResponseEntity(compositionView, HttpStatus.OK);
    }

   /* {
        "Name": "string",
            "BattleType": "Land Battles",
            "FactionId": 11,
            "AvatarId": 0,
            "Budget": 0,
            "Wins": 0,
            "Losses": 0,
            "Units": [

  ]
    }*/

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteComposition(final Integer id) {
        compositionService.deleteCompositionById(id);
        return ResponseEntity.ok().build();
    }
}
