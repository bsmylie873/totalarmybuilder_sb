package com.coe.totalarmybuilder.controllers;

import com.coe.totalarmybuilder.controller.AccountController;
import com.coe.totalarmybuilder.dto.Account.AccountDto;
import com.coe.totalarmybuilder.dto.Account.CreateAccountDto;
import com.coe.totalarmybuilder.dto.Account.UpdateAccountDto;
import com.coe.totalarmybuilder.exception.ExceptionHandler;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.model.view.account.AccountDetailView;
import com.coe.totalarmybuilder.model.view.account.AccountView;
import com.coe.totalarmybuilder.model.view.account.CreateAccountView;
import com.coe.totalarmybuilder.model.view.account.UpdateAccountView;
import com.coe.totalarmybuilder.service.AccountService;
import com.coe.totalarmybuilder.util.ResourceUtility;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.rules.FixtureRule;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTests {

    private MockMvc mockMvc;

    //private static final String CREATE_ACCOUNT_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/CreateAccount_Valid.json");

    //private static final String UPDATE_ACCOUNT_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/UpdateAccount_Valid.json");

    @Mock
    private Mapper mapperMock;

    @Mock
    private AccountService accountServiceMock;

    @Fixture
    private AccountDto accountDtoFixture;

    @Fixture
    private AccountView accountViewFixture;

    @Fixture
    private List<AccountView> accountViewListFixture;

    @Fixture
    private AccountDetailView accountDetailViewFixture;

    @Fixture
    private List<AccountDto> accountDtoListFixture;

    @Fixture
    private CreateAccountDto createAccountDtoFixture;

    @Fixture
    private UpdateAccountDto updateAccountDtoFixture;

    @Fixture
    private CreateAccountView createAccountViewFixture;

    @Fixture
    private UpdateAccountView updateAccountViewFixture;



    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<AccountView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new AccountController(accountServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void getAccounts_Returns_List_Of_Accounts_And_Ok() throws Exception {
        when(mapperMock.map(accountDtoListFixture, AccountView.class)).thenReturn(accountViewListFixture);
        when(accountServiceMock.findAll()).thenReturn(accountDtoListFixture);
        mockMvc.perform(get("/api/accounts")).andExpect(status().isOk());
    }

    @Test
    public void getAccountById_Returns_Account_And_Ok() throws Exception {
        when(mapperMock.map(accountDtoFixture, AccountDetailView.class)).thenReturn(accountDetailViewFixture);
        when(accountServiceMock.findById(anyInt())).thenReturn(accountDtoFixture);
        mockMvc.perform(get("/api/accounts/1")).andExpect(status().isOk());
    }

 /*   @Test
    public void createAccount_Returns_Created() throws Exception {
        when(mapperMock.map(any(CreateAccountView.class), eq(CreateAccountDto.class))).thenReturn(createAccountDtoFixture);
        when(accountServiceMock.createAccount(createAccountDtoFixture)).thenReturn(createAccountDtoFixture);
        when(mapperMock.map(createAccountDtoFixture, AccountView.class)).thenReturn(accountViewFixture);
        mockMvc.perform(post("/api/accounts").contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_ORDER_VALID_JSON)).andExpect(status().isCreated());
    }*/

}
