package com.coe.totalarmybuilder.model.view.unit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnitView {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Cost")
    private int cost;

    @JsonProperty("AvatarId")
    private int avatarId;
}
