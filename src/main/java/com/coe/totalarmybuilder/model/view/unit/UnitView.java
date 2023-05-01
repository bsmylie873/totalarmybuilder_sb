package com.coe.totalarmybuilder.model.view.unit;

import com.coe.totalarmybuilder.model.view.faction.FactionView;
import com.coe.totalarmybuilder.model.view.composition.CompositionView;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnitView {

    @JsonProperty("Id")
    private int Id;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("Cost")
    private int Cost;

    @JsonProperty("AvatarId")
    private int AvatarId;

    @JsonProperty("Compositions")
    private List<CompositionView> Compositions;

    @JsonProperty("Factions")
    private List<FactionView> Factions;
}
