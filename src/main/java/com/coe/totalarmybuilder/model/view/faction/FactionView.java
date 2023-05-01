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
public class FactionView {

    @JsonProperty("Id")
    private int Id;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("Units")
    private List<UnitView> Units;
}
