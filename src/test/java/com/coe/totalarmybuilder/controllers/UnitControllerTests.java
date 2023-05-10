package com.coe.totalarmybuilder.controllers;

import com.coe.totalarmybuilder.controller.UnitController;
import com.coe.totalarmybuilder.dto.Faction.FactionDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import com.coe.totalarmybuilder.exception.ExceptionHandler;
import com.coe.totalarmybuilder.mapper.Mapper;
import com.coe.totalarmybuilder.model.view.account.AccountView;
import com.coe.totalarmybuilder.model.view.faction.FactionView;
import com.coe.totalarmybuilder.model.view.unit.UnitDetailView;
import com.coe.totalarmybuilder.model.view.unit.UnitView;
import com.coe.totalarmybuilder.service.UnitService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UnitControllerTests {

    private MockMvc mockMvc;

    @Mock
    private Mapper mapperMock;

    @Mock
    private UnitService unitServiceMock;

    @Fixture
    private UnitDto unitDtoFixture;

    @Fixture
    private UnitView unitViewFixture;

    @Fixture
    private UnitDetailView unitDetailViewFixture;

    @Fixture
    private List<UnitDto> unitDtoListFixture;

    @Fixture
    private List<UnitView> unitViewListFixture;

    @Fixture
    private List<FactionDto> factionDtoListFixture;

    @Fixture
    private List<FactionView> factionViewListFixture;

    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<AccountView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new UnitController(unitServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void getUnits_Returns_List_Of_Units_And_Ok() throws Exception {
        when(mapperMock.map(unitDtoListFixture, UnitView.class)).thenReturn(unitViewListFixture);
        when(unitServiceMock.findAll()).thenReturn(unitDtoListFixture);
        mockMvc.perform(get("/api/units")).andExpect(status().isOk());
    }

    @Test
    public void getUnitById_Returns_Unit_And_Ok() throws Exception {
        when(mapperMock.map(unitDtoFixture, UnitDetailView.class)).thenReturn(unitDetailViewFixture);
        when(unitServiceMock.findById(anyInt())).thenReturn(unitDtoFixture);
        mockMvc.perform(get("/api/units/1")).andExpect(status().isOk());
    }

    @Test
    public void getUnitsByFactionId_Returns_List_Of_Units_And_Ok() throws Exception {
        when(mapperMock.map(factionDtoListFixture, FactionView.class)).thenReturn(factionViewListFixture);
        when(unitServiceMock.findFactionsByUnitId(anyInt())).thenReturn(factionDtoListFixture);
        mockMvc.perform(get("/api/units/1/factions/")).andExpect(status().isOk());
    }

    @Test
    public void getUnitLords_Returns_List_Of_Lords_And_Ok() throws Exception {
        when(mapperMock.map(unitDtoListFixture, UnitView.class)).thenReturn(unitViewListFixture);
        when(unitServiceMock.findLords()).thenReturn(unitDtoListFixture);
        mockMvc.perform(get("/api/units/lords/")).andExpect(status().isOk());
    }

    @Test
    public void getUnitHeroes_Returns_List_Of_Lords_And_Ok() throws Exception {
        when(mapperMock.map(unitDtoListFixture, UnitView.class)).thenReturn(unitViewListFixture);
        when(unitServiceMock.findHeroes()).thenReturn(unitDtoListFixture);
        mockMvc.perform(get("/api/units/heroes/")).andExpect(status().isOk());
    }
}
