package com.coe.totalarmybuilder.controllers;

import com.coe.totalarmybuilder.controller.CompositionController;
import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.dto.Composition.CreateCompositionDto;
import com.coe.totalarmybuilder.dto.Composition.UpdateCompositionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.exception.ExceptionHandler;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.model.view.composition.CompositionDetailView;
import com.coe.totalarmybuilder.model.view.composition.CompositionView;
import com.coe.totalarmybuilder.model.view.composition.CreateCompositionView;
import com.coe.totalarmybuilder.model.view.composition.UpdateCompositionView;
import com.coe.totalarmybuilder.model.view.unit.UnitView;
import com.coe.totalarmybuilder.service.CompositionService;
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
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CompositionControllerTests {

    private MockMvc mockMvc;
    private static final String CREATE_COMPOSITION_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/CreateComposition_Valid.json");

    private static final String UPDATE_COMPOSITION_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/UpdateComposition_Valid.json");

    @Mock
    private Mapper mapperMock;

    @Mock
    private CompositionService compositionServiceMock;

    @Fixture
    private CompositionDto compositionDtoFixture;

    @Fixture
    private CompositionView compositionViewFixture;

    @Fixture
    private List<CompositionView> compositionViewListFixture;

    @Fixture
    private CompositionDetailView compositionDetailViewFixture;

    @Fixture
    private List<UnitView> unitListViewFixture;

    @Fixture
    private List<CompositionDto> compositionDtoListFixture;

    @Fixture
    private List<UnitDto> unitDtoListFixture;

    @Fixture
    private CreateCompositionDto createCompositionDtoFixture;

    @Fixture
    private UpdateCompositionDto updateCompositionDtoFixture;

    @Fixture
    private CreateCompositionView createCompositionViewFixture;

    @Fixture
    private UpdateCompositionView updateCompositionViewFixture;

    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<CompositionView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new CompositionController(compositionServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void getCompositions_Returns_List_Of_Compositions_And_Ok() throws Exception {
        when(mapperMock.map(compositionDtoListFixture, CompositionView.class)).thenReturn(compositionViewListFixture);
        when(compositionServiceMock.findAll()).thenReturn(compositionDtoListFixture);
        mockMvc.perform(get("/api/compositions")).andExpect(status().isOk());
    }

    @Test
    public void getCompositionById_Returns_Composition_And_Ok() throws Exception {
        when(mapperMock.map(compositionDtoFixture, CompositionDetailView.class)).thenReturn(compositionDetailViewFixture);
        when(compositionServiceMock.findById(anyInt())).thenReturn(compositionDtoFixture);
        mockMvc.perform(get("/api/compositions/1")).andExpect(status().isOk());
    }

    @Test
    public void getUnitsByCompositionId_Returns_Units_And_Ok() throws Exception {
        when(mapperMock.map(unitDtoListFixture, UnitView.class)).thenReturn(unitListViewFixture);
        when(compositionServiceMock.findUnitsByCompositionId(anyInt())).thenReturn(unitDtoListFixture);
        mockMvc.perform(get("/api/compositions/1/units/")).andExpect(status().isOk());
    }

    @Test
    public void createComposition_Returns_Created() throws Exception {
        when(mapperMock.map(any(CreateCompositionView.class), eq(CreateCompositionDto.class))).thenReturn(createCompositionDtoFixture);
        when(compositionServiceMock.createComposition(createCompositionDtoFixture)).thenReturn(compositionDtoFixture);
        when(mapperMock.map(compositionDtoFixture, CompositionView.class)).thenReturn(compositionViewFixture);
        mockMvc.perform(post("/api/compositions").contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_COMPOSITION_VALID_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void updateComposition_Returns_Created() throws Exception {
        when(mapperMock.map(any(UpdateCompositionView.class), eq(UpdateCompositionDto.class))).thenReturn(updateCompositionDtoFixture);
        when(compositionServiceMock.updateComposition(anyInt(), any(UpdateCompositionDto.class))).thenReturn(Optional.of(compositionDtoFixture));
        when(mapperMock.map(compositionDtoFixture, CompositionView.class)).thenReturn(compositionViewFixture);
        mockMvc.perform(patch("/api/compositions/1").contentType(MediaType.APPLICATION_JSON)
                .content(UPDATE_COMPOSITION_VALID_JSON)).andExpect(status().isOk());
    }
}
