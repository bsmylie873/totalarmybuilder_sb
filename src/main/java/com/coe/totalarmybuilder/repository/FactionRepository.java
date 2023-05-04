package com.coe.totalarmybuilder.repository;


import com.coe.totalarmybuilder.entity.Faction;
import com.coe.totalarmybuilder.entity.Unit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactionRepository extends CrudRepository<Faction, Integer> {

    List<Faction> findAll();

    Optional<Faction> findById(int factionId);

    @Query( "SELECT U FROM Unit U JOIN UnitFaction UF WHERE UF.factionId = :id AND UF.unitId= U.id")
    List<Unit> findUnitsByFactionId(Integer id);

}
