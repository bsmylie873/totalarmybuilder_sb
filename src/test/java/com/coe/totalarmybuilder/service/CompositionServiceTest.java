package com.coe.totalarmybuilder.service;

import com.coe.totalarmybuilder.dto.Account.AccountDto;
import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.dto.Composition.CreateCompositionDto;
import com.coe.totalarmybuilder.dto.Composition.UpdateCompositionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.entity.Account;
import com.coe.totalarmybuilder.entity.Composition;
import com.coe.totalarmybuilder.entity.Unit;
import com.coe.totalarmybuilder.exception.custom.ResourceNotFoundException;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.repository.CompositionRepository;
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
public class CompositionServiceTest {

    @Fixture
    private List<Composition> compositionListFixture;

    @Fixture
    private List<CompositionDto> compositionDtoListFixture;

    @Fixture
    private Composition compositionFixture;

    @Fixture
    private CompositionDto compositionDtoFixture;

    @Fixture
    private List<Unit> unitListFixture;

    @Fixture
    private List<UnitDto> unitDtoListFixture;

    @Fixture
    private CreateCompositionDto createCompositionDtoFixture;

    @Fixture
    private UpdateCompositionDto updateCompositionDtoFixture;

    @Mock
    private CompositionRepository compositionRepositoryMock;

    @Mock
    private Mapper mapperMock;

    @InjectMocks
    private CompositionService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void findAll_Returns_List_Of_Compositions() {

        // Arrange
        when(compositionRepositoryMock.findAll()).thenReturn(compositionListFixture);
        when(mapperMock.map(compositionListFixture, CompositionDto.class)).thenReturn(compositionDtoListFixture);

        // Act
        List<CompositionDto> compositionDtoList = classUnderTest.findAll();

        // Assert
        assertThat(compositionDtoList).isNotNull();
        assertThat(compositionDtoList).usingRecursiveComparison().isEqualTo(compositionDtoListFixture);
        verify(compositionRepositoryMock, times(1)).findAll();
    }

    @Test
    public void findById_Returns_Composition_By_Id() {

        // Arrange
        when(compositionRepositoryMock.findById(anyInt())).thenReturn(Optional.of(compositionFixture));
        when(mapperMock.map((compositionFixture), CompositionDto.class)).thenReturn(compositionDtoFixture);

        // Act
        CompositionDto CompositionDto = classUnderTest.findById(anyInt());

        // Assert
        assertThat(CompositionDto).isNotNull();
        assertThat(CompositionDto).usingRecursiveComparison().isEqualTo(compositionDtoFixture);
        verify(compositionRepositoryMock, times(1)).findById(anyInt());
    }

    @Test
    public void findUnitsByCompositionId_Returns_List_Of_Units() {

        // Arrange
        when(compositionRepositoryMock.findUnitsByCompositionId(anyInt())).thenReturn(unitListFixture);
        when(mapperMock.map(unitListFixture, UnitDto.class)).thenReturn(unitDtoListFixture);

        // Act
        List<UnitDto> unitDtoList = classUnderTest.findUnitsByCompositionId(anyInt());

        // Assert
        assertThat(unitDtoList).isNotNull();
        assertThat(unitDtoList).usingRecursiveComparison().isEqualTo(unitDtoListFixture);
        verify(compositionRepositoryMock, times(1)).findUnitsByCompositionId(anyInt());
    }

    @Test
    public void createComposition_Returns_CompositionDto() {

        // Arrange
        when(mapperMock.map(createCompositionDtoFixture, Composition.class)).thenReturn(compositionFixture);
        when(mapperMock.map(compositionFixture, CompositionDto.class)).thenReturn(compositionDtoFixture);
        when(compositionRepositoryMock.save(compositionFixture)).thenReturn(compositionFixture);

        // Act
        CompositionDto CompositionDto = classUnderTest.createComposition(createCompositionDtoFixture);

        // Assert
        verify(compositionRepositoryMock).save(compositionFixture);
        assertThat(CompositionDto).usingRecursiveComparison().isEqualTo(compositionDtoFixture);
    }

    @Test
    public void update_Composition_Returns_CompositionDto() {

        // Arrange
        when(mapperMock.map(compositionFixture, CompositionDto.class)).thenReturn(compositionDtoFixture);
        when(compositionRepositoryMock.existsById(anyInt())).thenReturn(true);
        when(mapperMock.map(updateCompositionDtoFixture, Composition.class)).thenReturn(compositionFixture);
        when(compositionRepositoryMock.save(compositionFixture)).thenReturn(compositionFixture);

        // Act
        Optional<CompositionDto> compositionDto = classUnderTest.updateComposition(anyInt(), updateCompositionDtoFixture);

        // Assert
        verify(compositionRepositoryMock).save(compositionFixture);
        assertThat(compositionDto.get()).usingRecursiveComparison().isEqualTo(compositionDtoFixture);
    }

    @Test
    public void deleteCompositionById_Returns_ResourceNotFoundException() {

        // Act
        doThrow(ResourceNotFoundException.class).when(compositionRepositoryMock).deleteById(anyInt());

        // Assert
        assertThrows(ResourceNotFoundException.class,
                () -> classUnderTest.deleteCompositionById(anyInt()),
                "ResourceNotFoundException is expected");
        verify(compositionRepositoryMock, times(1)).deleteById(anyInt());
    }


}
