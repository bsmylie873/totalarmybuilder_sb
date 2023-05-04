package com.coe.totalarmybuilder.model.view.unit;

import com.coe.totalarmybuilder.model.view.faction.FactionView;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnitDetailView {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Cost")
    private int cost;

    @JsonProperty("AvatarId")
    private int avatarId;

    @JsonProperty("Factions")
    private List<FactionView> factions;

}
