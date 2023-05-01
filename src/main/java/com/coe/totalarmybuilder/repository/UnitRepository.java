package com.coe.totalarmybuilder.repository;

import com.coe.totalarmybuilder.entity.Composition;
import com.coe.totalarmybuilder.entity.Faction;
import com.coe.totalarmybuilder.entity.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Integer> {

    List<Unit> findAll();

    Optional<Unit> findById(int unitId);

    List<Faction> findAllById(Integer id);
}