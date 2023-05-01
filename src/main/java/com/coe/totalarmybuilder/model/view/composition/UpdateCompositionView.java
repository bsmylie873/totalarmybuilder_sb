package com.coe.totalarmybuilder.model.view.composition;

import com.coe.totalarmybuilder.model.view.unit.UnitView;
import com.coe.totalarmybuilder.model.view.account.AccountView;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class UpdateCompositionView {
    @JsonProperty("Name")
    private String Name;

    @JsonProperty("FactionId")
    private int FactionId;

    @JsonProperty("AvatarId")
    private int AvatarId;

    @JsonProperty("Budget")
    private int Budget;

    @JsonProperty("DateCreated")
    private Date DateCreated;

    @JsonProperty("Wins")
    private int Wins;

    @JsonProperty("Losses")
    private int Losses;

    @JsonProperty("Accounts")
    private List<AccountView> Accounts;

    @JsonProperty("Units")
    private List<UnitView> Units;
}
