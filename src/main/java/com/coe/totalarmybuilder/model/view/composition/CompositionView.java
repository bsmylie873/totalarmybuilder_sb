package com.coe.totalarmybuilder.model.view.composition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompositionView {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("BattleType")
    private String battleType;

    @JsonProperty("FactionId")
    private int factionId;

    @JsonProperty("AvatarId")
    private int avatarId;

    @JsonProperty("Budget")
    private int budget;

    @JsonProperty("DateCreated")
    private Date dateCreated;

    @JsonProperty("Wins")
    private int wins;

    @JsonProperty("Losses")
    private int losses;

}
