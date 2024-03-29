package com.coe.totalarmybuilder.repository;

import com.coe.totalarmybuilder.entity.Faction;
import com.coe.totalarmybuilder.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    List<Unit> findAll();

    Optional<Unit> findById(Integer id);

    @Query( "SELECT F FROM Faction F JOIN UnitFaction UF WHERE UF.unitId = :id AND UF.factionId = F.id")
    List<Faction> findFactionsByUnitId(Integer id);

    @Query ("SELECT U FROM Unit U JOIN LordUnit LU WHERE LU.unit = :id AND LU.unit = U.lordUnit")
    List<Unit> findLords();

    @Query ("SELECT U FROM Unit U JOIN HeroUnit HU WHERE HU.unit = :id AND HU.unit = U.lordUnit")
    List<Unit> findHeroes();
}