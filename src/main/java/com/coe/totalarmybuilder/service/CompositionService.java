package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Account.AccountDto;
import com.coe.totalarmybuilder.dto.Account.CreateAccountDto;
import com.coe.totalarmybuilder.dto.Account.UpdateAccountDto;
import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.dto.Composition.CreateCompositionDto;
import com.coe.totalarmybuilder.dto.Composition.UpdateCompositionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.entity.Account;
import com.coe.totalarmybuilder.entity.Composition;
import com.coe.totalarmybuilder.exception.custom.ResourceNotFoundException;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.CompositionRepository;
import lombok.RequiredArgsConstructor;
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
        return mapper.map(compositionRepository.findById(id),  CompositionDto.class);
    }
    public List<UnitDto> findAllById(Integer id)  {
        return mapper.map(compositionRepository.findAllById(id),  UnitDto.class);
    }

    public CompositionDto createComposition(final CreateCompositionDto createCompositionDto) {
        final Composition composition = mapper.map(createCompositionDto, Composition.class);
        final Composition newComposition;
        newComposition = compositionRepository.save(composition);
        return mapper.map(newComposition, CompositionDto.class);
    }

    public Optional<CompositionDto> updateComposition(final int id, final UpdateCompositionDto updateCompositionDto) {
        final Optional<Composition> composition = compositionRepository.findById(id);
        if (composition.isEmpty()) return Optional.empty();
        updateCompositionEntity(updateCompositionDto, composition.get());
        return Optional.of(mapper.map(composition.get(), CompositionDto.class));
    }

    private void updateCompositionEntity(final UpdateCompositionDto updateCompositionDto, final Composition composition) {
        composition.setName(updateCompositionDto.getName());
        composition.setBattleType(updateCompositionDto.getBattleType());
        composition.setFactionId(updateCompositionDto.getFactionId());
        composition.setAvatarId(updateCompositionDto.getAvatarId());
        composition.setBudget(updateCompositionDto.getBudget());
        composition.setWins(updateCompositionDto.getWins());
        composition.setLosses(updateCompositionDto.getLosses());
        //composition.setAccounts(updateCompositionDto.getAccounts());
        //composition.setUnits(updateCompositionDto.getUnits());
        compositionRepository.save(composition);
    }

    public void deleteCompositionById(Integer id) {
        if(!compositionRepository.existsById(id)){
            throw new ResourceNotFoundException("Unable to find composition id");
        }
        compositionRepository.deleteById(id);
    }
}
