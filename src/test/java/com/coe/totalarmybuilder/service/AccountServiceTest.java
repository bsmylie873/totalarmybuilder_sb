package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Account.AccountDto;
import com.coe.totalarmybuilder.dto.Account.CreateAccountDto;
import com.coe.totalarmybuilder.dto.Account.UpdateAccountDto;
import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.entity.Account;
import com.coe.totalarmybuilder.entity.Composition;
import com.coe.totalarmybuilder.exception.custom.ResourceNotFoundException;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.AccountRepository;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Fixture
    private List<Account> accountListFixture;

    @Fixture
    private List<AccountDto> accountDtoListFixture;

    @Fixture
    private Account accountFixture;

    @Fixture
    private AccountDto accountDtoFixture;

    @Fixture
    private List<Composition> compositionListFixture;

    @Fixture
    private List<CompositionDto> compositionDtoListFixture;

    @Fixture
    private CreateAccountDto createAccountDtoFixture;

    @Fixture
    private UpdateAccountDto updateAccountDtoFixture;

    @Mock
    private EntityManager entityManagerMock;

    @Mock
    private AccountRepository accountRepositoryMock;

    @Mock
    private Mapper mapperMock;

    @InjectMocks
    private AccountService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void findAll_Returns_List_Of_Accounts() {

        // Arrange
        when(accountRepositoryMock.findAll()).thenReturn(accountListFixture);
        when(mapperMock.map(accountListFixture, AccountDto.class)).thenReturn(accountDtoListFixture);

        // Act
        List<AccountDto> accountDtoList = classUnderTest.findAll();

        // Assert
        assertThat(accountDtoList).isNotNull();
        assertThat(accountDtoList).usingRecursiveComparison().isEqualTo(accountDtoListFixture);
        verify(accountRepositoryMock, times(1)).findAll();
    }

    @Test
    public void findById_Returns_Account_By_Id() {

        // Arrange
        when(accountRepositoryMock.findById(anyInt())).thenReturn(Optional.of(accountFixture));
        when(mapperMock.map((accountFixture), AccountDto.class)).thenReturn(accountDtoFixture);

        // Act
        AccountDto accountDto = classUnderTest.findById(anyInt());

        // Assert
        assertThat(accountDto).isNotNull();
        assertThat(accountDto).usingRecursiveComparison().isEqualTo(accountDtoFixture);
        verify(accountRepositoryMock, times(1)).findById(anyInt());
    }

    @Test
    public void findCompositionsByAccountId_Returns_List_Of_Compositions() {

        // Arrange
        when(accountRepositoryMock.findCompositionsByAccountId(anyInt())).thenReturn(compositionListFixture);
        when(mapperMock.map(compositionListFixture, CompositionDto.class)).thenReturn(compositionDtoListFixture);

        // Act
        List<CompositionDto> compositionDtoList = classUnderTest.findCompositionsByAccountId(anyInt());

        // Assert
        assertThat(compositionDtoList).isNotNull();
        assertThat(compositionDtoList).usingRecursiveComparison().isEqualTo(compositionDtoListFixture);
        verify(accountRepositoryMock, times(1)).findCompositionsByAccountId(anyInt());
    }


    @Test
    public void createAccount_Returns_AccountDto() {

        // Arrange
        when(mapperMock.map(createAccountDtoFixture, Account.class)).thenReturn(accountFixture);
        when(mapperMock.map(accountFixture, AccountDto.class)).thenReturn(accountDtoFixture);
        when(accountRepositoryMock.save(accountFixture)).thenReturn(accountFixture);

        // Act
        AccountDto accountDto = classUnderTest.createAccount(createAccountDtoFixture);

        // Assert
        verify(accountRepositoryMock).save(accountFixture);
        assertThat(accountDto).usingRecursiveComparison().isEqualTo(accountDtoFixture);
    }

    @Test
    public void update_Account_Returns_AccountDto() {

        // Arrange
        when(mapperMock.map(accountFixture, AccountDto.class)).thenReturn(accountDtoFixture);
        when(accountRepositoryMock.existsById(anyInt())).thenReturn(true);
        when(mapperMock.map(updateAccountDtoFixture, Account.class)).thenReturn(accountFixture);
        when(accountRepositoryMock.save(accountFixture)).thenReturn(accountFixture);

        // Act
        Optional<AccountDto> accountDto = classUnderTest.updateAccount(anyInt(), updateAccountDtoFixture);

        // Assert
        verify(accountRepositoryMock).save(accountFixture);
        assertThat(accountDto.get()).usingRecursiveComparison().isEqualTo(accountDtoFixture);
    }

    @Test
    public void deleteAccountById_Returns_ResourceNotFoundException() {

        // Act
        doThrow(ResourceNotFoundException.class).when(accountRepositoryMock).deleteById(anyInt());

        // Assert
        assertThrows(ResourceNotFoundException.class,
                () -> classUnderTest.deleteAccountById(anyInt()),
                "ResourceNotFoundException is expected");
        verify(accountRepositoryMock, times(1)).deleteById(anyInt());
    }


}
