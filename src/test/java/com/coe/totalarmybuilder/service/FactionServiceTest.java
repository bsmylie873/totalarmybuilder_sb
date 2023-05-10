package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Faction.FactionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.entity.Faction;
import com.coe.totalarmybuilder.entity.Unit;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.FactionRepository;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FactionServiceTest {

    @Fixture
    private List<Faction> factionListFixture;

    @Fixture
    private List<FactionDto> factionDtoListFixture;

    @Fixture
    private Faction factionFixture;

    @Fixture
    private FactionDto factionDtoFixture;

    @Fixture
    private List<Unit> unitListFixture;

    @Fixture
    private List<UnitDto> unitDtoListFixture;

    @Mock
    private FactionRepository factionRepositoryMock;

    @Mock
    private Mapper mapperMock;

    @InjectMocks
    private FactionService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void findAll_Returns_List_Of_Factions() {

        // Arrange
        when(factionRepositoryMock.findAll()).thenReturn(factionListFixture);
        when(mapperMock.map(factionListFixture, FactionDto.class)).thenReturn(factionDtoListFixture);

        // Act
        List<FactionDto> FactionDtoList = classUnderTest.findAll();

        // Assert
        assertThat(FactionDtoList).isNotNull();
        assertThat(FactionDtoList).usingRecursiveComparison().isEqualTo(factionDtoListFixture);
        verify(factionRepositoryMock, times(1)).findAll();
    }

    @Test
    public void findById_Returns_Faction_By_Id() {

        // Arrange
        when(factionRepositoryMock.findById(anyInt())).thenReturn(Optional.of(factionFixture));
        when(mapperMock.map((factionFixture), FactionDto.class)).thenReturn(factionDtoFixture);

        // Act
        FactionDto FactionDto = classUnderTest.findById(anyInt());

        // Assert
        assertThat(FactionDto).isNotNull();
        assertThat(FactionDto).usingRecursiveComparison().isEqualTo(factionDtoFixture);
        verify(factionRepositoryMock, times(1)).findById(anyInt());
    }

    @Test
    public void findUnitsByFactionId_Returns_List_Of_Units() {

        // Arrange
        when(factionRepositoryMock.findUnitsByFactionId(anyInt())).thenReturn(unitListFixture);
        when(mapperMock.map(unitListFixture, UnitDto.class)).thenReturn(unitDtoListFixture);

        // Act
        List<UnitDto> unitDtoList = classUnderTest.findUnitsByFactionId(anyInt());

        // Assert
        assertThat(unitDtoList).isNotNull();
        assertThat(unitDtoList).usingRecursiveComparison().isEqualTo(unitDtoListFixture);
        verify(factionRepositoryMock, times(1)).findUnitsByFactionId(anyInt());
    }

}
