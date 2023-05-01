package com.coe.totalarmybuilder.repository;

import com.coe.totalarmybuilder.entity.Account;
import com.coe.totalarmybuilder.entity.Composition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

    List<Account> findAll();

    Optional<Account> findById(Integer id);

    List<Composition> findAllById(Integer id);
}