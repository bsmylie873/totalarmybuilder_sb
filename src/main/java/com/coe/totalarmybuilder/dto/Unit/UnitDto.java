package com.coe.totalarmybuilder.dto.Unit;
import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import com.coe.totalarmybuilder.dto.Faction.FactionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnitDto {

    public int id;
    public String name;
    public int cost;
    public int avatarId;

    public List<CompositionDto> compositions;
    public List<FactionDto> factions;
}
