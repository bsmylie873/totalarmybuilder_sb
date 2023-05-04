package com.coe.totalarmybuilder.model.view.composition;

import com.coe.totalarmybuilder.model.view.account.AccountView;
import com.coe.totalarmybuilder.model.view.unit.UnitView;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompositionDetailView {

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

    @JsonProperty("Accounts")
    private List<AccountView> accounts;

    @JsonProperty("Units")
    private List<UnitView> units;
}
