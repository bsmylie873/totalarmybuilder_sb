package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.dto.Composition.CreateCompositionDto;
import com.coe.totalarmybuilder.dto.Composition.UpdateCompositionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.entity.Composition;
import com.coe.totalarmybuilder.exception.custom.ResourceNotFoundException;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.CompositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompositionService {

    private final CompositionRepository compositionRepository;
    private final Mapper mapper;

    public List<CompositionDto> findAll() {
        return mapper.map(compositionRepository.findAll(), CompositionDto.class);
    }
    public CompositionDto findById(Integer id) {
        return mapper.map(compositionRepository.findById(id).get(),  CompositionDto.class);
    }
    public List<UnitDto> findUnitsByComposition(Integer id)  {
        return mapper.map(compositionRepository.findUnitsByComposition(id),  UnitDto.class);
    }

    public CompositionDto createComposition(final CreateCompositionDto createCompositionDto) {
        final Composition composition = mapper.map(createCompositionDto, Composition.class);
        final Composition newComposition;
        newComposition = compositionRepository.save(composition);
        return mapper.map(newComposition, CompositionDto.class);
    }

    public Optional<CompositionDto> updateComposition(final int id, final UpdateCompositionDto updateCompositionDto) {
        if (!(compositionRepository.existsById(id))) {
            return Optional.empty();
        }
        Composition composition = mapper.map(updateCompositionDto, Composition.class);
        composition.setId(id);
        compositionRepository.save(composition);
        return Optional.of(mapper.map(composition, CompositionDto.class));
    }

    public void deleteCompositionById(Integer id) {
        try {
            compositionRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Unable to find composition");
        }
    }
}
