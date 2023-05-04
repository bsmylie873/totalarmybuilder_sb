package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Account.AccountDto;
import com.coe.totalarmybuilder.dto.Account.CreateAccountDto;
import com.coe.totalarmybuilder.dto.Account.UpdateAccountDto;
import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.entity.Account;
import com.coe.totalarmybuilder.exception.custom.ResourceNotFoundException;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final Mapper mapper;

    public List<AccountDto> findAll() {
        return mapper.map(accountRepository.findAll(), AccountDto.class);
    }

    public AccountDto findById(final int id) {
        return mapper.map(accountRepository.findById(id).get(), AccountDto.class);
    }

    public List<CompositionDto> findCompositionsByAccountId(final int id) {
        return mapper.map(accountRepository.findCompositionsByAccountId(id), CompositionDto.class);
    }

    public AccountDto createAccount(final CreateAccountDto createAccountDto) {
        final Account account = mapper.map(createAccountDto, Account.class);
        final Account newAccount;
        newAccount = accountRepository.save(account);
        return mapper.map(newAccount, AccountDto.class);
    }

    public Optional<AccountDto> updateAccount(final int id, final UpdateAccountDto updateAccountDto) {
        if (!(accountRepository.existsById(id))) {
            return Optional.empty();
        }
        Account account = mapper.map(updateAccountDto, Account.class);
        account.setId(id);
        accountRepository.save(account);
        return Optional.of(mapper.map(account, AccountDto.class));
    }

    public void deleteAccountById(Integer id) {
        try {
            accountRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Unable to find account");
        }
    }
}