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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final Mapper mapper;
    public List<AccountDto> findAll() {
        return mapper.map(accountRepository.findAll(),  AccountDto.class);
    }
    public AccountDto findById(Integer id) {
        return mapper.map(accountRepository.findById(id),  AccountDto.class);
    }
    public List<CompositionDto> findAllById(Integer id)  {
        return mapper.map(accountRepository.findAllById(id),  CompositionDto.class);
    }
    public AccountDto createAccount(final CreateAccountDto createAccountDto) {
        final Account account = mapper.map(createAccountDto, Account.class);
        final Account newAccount;
        newAccount = accountRepository.save(account);
        return mapper.map(newAccount, AccountDto.class);
    }

    public Optional<AccountDto> updateAccount(final int id, final UpdateAccountDto updateAccountDto) {
        final Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) return Optional.empty();
        updateAccountEntity(updateAccountDto, account.get());
        return Optional.of(mapper.map(account.get(), AccountDto.class));
    }

    private void updateAccountEntity(final UpdateAccountDto updateAccountDto, final Account account) {
        account.setUsername(updateAccountDto.getUsername());
        account.setEmail(updateAccountDto.getEmail());
        account.setPassword(updateAccountDto.getPassword());
        accountRepository.save(account);
    }

    public void deleteAccountById(Integer id) {
        if(!accountRepository.existsById(id)){
            throw new ResourceNotFoundException("Unable to find account id");
        }
        accountRepository.deleteById(id);
    }


}
