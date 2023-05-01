package com.coe.totalarmybuilder.repository;

import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.entity.Composition;
import com.coe.totalarmybuilder.entity.Faction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactionRepository extends CrudRepository<Faction, Integer> {

    List<Faction> findAll();

    Optional<Faction> findById(int factionId);
    List<UnitDto> findAllById(Integer id);
}
