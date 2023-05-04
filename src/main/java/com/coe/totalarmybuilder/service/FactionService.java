package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Faction.FactionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.FactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactionService {
    private final FactionRepository factionRepository;
    private final Mapper mapper;

    public List<FactionDto> findAll() {
        return mapper.map(factionRepository.findAll(), FactionDto.class);
    }
    public FactionDto findById(Integer id) {
        return mapper.map(factionRepository.findById(id).get(),  FactionDto.class);
    }
    public List<UnitDto> findUnitsByFactionId(Integer id) {
        return mapper.map(factionRepository.findUnitsByFactionId(id),  UnitDto.class);
    }

}
