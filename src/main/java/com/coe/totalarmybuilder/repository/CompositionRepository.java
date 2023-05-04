package com.coe.totalarmybuilder.repository;

import com.coe.totalarmybuilder.entity.Composition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompositionRepository extends CrudRepository<Composition, Integer> {

    List<Composition> findAll();

    Optional<Composition> findById(Integer compositionId);

    @Query( "SELECT U FROM Unit U JOIN CompositionUnit CU WHERE CU.compositionId = :id AND CU.unitId= U.id")
    List<Composition> findUnitsByComposition(Integer id);
}
