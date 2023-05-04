package com.coe.totalarmybuilder.model.view.faction;

import com.coe.totalarmybuilder.model.view.unit.UnitView;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FactionDetailView {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Units")
    private List<UnitView> units;

}
