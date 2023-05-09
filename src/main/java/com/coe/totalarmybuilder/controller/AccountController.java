package com.coe.totalarmybuilder.controller;
import com.coe.totalarmybuilder.dto.Account.AccountDto;
import com.coe.totalarmybuilder.dto.Account.CreateAccountDto;
import com.coe.totalarmybuilder.dto.Account.UpdateAccountDto;
import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.model.view.account.AccountDetailView;
import com.coe.totalarmybuilder.model.view.account.AccountView;
import com.coe.totalarmybuilder.model.view.account.CreateAccountView;
import com.coe.totalarmybuilder.model.view.account.UpdateAccountView;
import com.coe.totalarmybuilder.model.view.composition.CompositionView;
import com.coe.totalarmybuilder.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountView>> getAccounts() {
        List<AccountDto> accountList = accountService.findAll();
        return ResponseEntity.ok(mapper.map(accountList, AccountView.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetailView> getAccountById(@PathVariable final Integer id) {
        AccountDto account = accountService.findById(id);
        return ResponseEntity.ok(mapper.map(account, AccountDetailView.class));
    }

    @GetMapping(value = "/{id}/compositions/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompositionView>> getAccountCompositions(@PathVariable final Integer id) {
        List<CompositionDto> accountCompositions = accountService.findCompositionsByAccountId(id);
        return ResponseEntity.ok(mapper.map(accountCompositions, CompositionView.class));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createAccount(@RequestBody final CreateAccountView createAccountView) {
        final CreateAccountDto createAccountDto = mapper.map(createAccountView, CreateAccountDto.class);
        final AccountDto accountDto = accountService.createAccount(createAccountDto);
        final AccountView accountView = mapper.map(accountDto, AccountView.class);
        return new ResponseEntity(accountView, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> updateAccount(@PathVariable final int id,
                                                    @RequestBody final UpdateAccountView updateAccountView) {
        final UpdateAccountDto updateAccountDto = mapper.map(updateAccountView, UpdateAccountDto.class);
        final Optional<AccountDto> accountDto = accountService.updateAccount(id, updateAccountDto);
        final AccountView accountView = mapper.map(accountDto.get(), AccountView.class);
        return new ResponseEntity(accountView, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable final Integer id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok().build();
    }

}

