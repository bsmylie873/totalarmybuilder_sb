package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Faction.FactionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {
    private final UnitRepository unitRepository;
    private final Mapper mapper;

    public List<UnitDto> findAll() {
        return mapper.map(unitRepository.findAll(), UnitDto.class);
    }
    public UnitDto findById(Integer id) {
        return mapper.map(unitRepository.findById(id),  UnitDto.class);
    }
    public List<FactionDto> findFactions(Integer id) {
        return mapper.map(unitRepository.findFactionsByUnitId(id),  FactionDto.class);
    }
   /* public List<UnitDto> findLords() {
        return mapper.map(unitRepository.findLords(), UnitDto.class);
    };
    public List<UnitDto> findHeroes() {
        return mapper.map(unitRepository.findHeroes(), UnitDto.class);
    };*/
}
