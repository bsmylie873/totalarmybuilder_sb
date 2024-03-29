package com.coe.totalarmybuilder.model.view.faction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FactionView {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

}
