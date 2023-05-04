package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Faction.FactionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.entity.Faction;
import com.coe.totalarmybuilder.entity.Unit;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.UnitRepository;
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
public class UnitServiceTest {

    @Fixture
    private List<Unit> unitListFixture;

    @Fixture
    private List<UnitDto> unitDtoListFixture;

    @Fixture
    private Unit unitFixture;

    @Fixture
    private UnitDto unitDtoFixture;

    @Fixture
    private List<Faction> factionListFixture;

    @Fixture
    private List<FactionDto> factionDtoListFixture;

    @Mock
    private UnitRepository unitRepositoryMock;

    @Mock
    private Mapper mapperMock;

    @InjectMocks
    private UnitService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void findAll_Returns_List_Of_units() {

        // Arrange
        when(unitRepositoryMock.findAll()).thenReturn(unitListFixture);
        when(mapperMock.map(unitListFixture, UnitDto.class)).thenReturn(unitDtoListFixture);

        // Act
        List<UnitDto> unitDtoList = classUnderTest.findAll();

        // Assert
        assertThat(unitDtoList).isNotNull();
        assertThat(unitDtoList).usingRecursiveComparison().isEqualTo(unitDtoListFixture);
        verify(unitRepositoryMock, times(1)).findAll();
    }

    @Test
    public void findById_Returns_unit_By_Id() {

        // Arrange
        when(unitRepositoryMock.findById(anyInt())).thenReturn(Optional.of(unitFixture));
        when(mapperMock.map((unitFixture), UnitDto.class)).thenReturn(unitDtoFixture);

        // Act
        UnitDto unitDto = classUnderTest.findById(anyInt());

        // Assert
        assertThat(unitDto).isNotNull();
        assertThat(unitDto).usingRecursiveComparison().isEqualTo(unitDtoFixture);
        verify(unitRepositoryMock, times(1)).findById(anyInt());
    }

    @Test
    public void findFactionsByUnitId_Returns_List_Of_Factions() {

        // Arrange
        when(unitRepositoryMock.findFactionsByUnitId(anyInt())).thenReturn(factionListFixture);
        when(mapperMock.map(factionListFixture, FactionDto.class)).thenReturn(factionDtoListFixture);

        // Act
        List<FactionDto> factionDtoList = classUnderTest.findFactionsByUnitId(anyInt());

        // Assert
        assertThat(factionDtoList).isNotNull();
        assertThat(factionDtoList).usingRecursiveComparison().isEqualTo(factionDtoListFixture);
        verify(unitRepositoryMock, times(1)).findFactionsByUnitId(anyInt());
    }

}
