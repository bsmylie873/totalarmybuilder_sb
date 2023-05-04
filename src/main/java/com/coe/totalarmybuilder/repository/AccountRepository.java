package com.coe.totalarmybuilder.repository;

import com.coe.totalarmybuilder.entity.Account;
import com.coe.totalarmybuilder.entity.Composition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

    List<Account> findAll();

    Optional<Account> findById(Integer id);

    @Query( "SELECT C FROM Composition C JOIN AccountComposition AC WHERE AC.accountId = :id AND AC.compositionId= C.id")
    List<Composition> findCompositionsByAccountId(Integer id);

}