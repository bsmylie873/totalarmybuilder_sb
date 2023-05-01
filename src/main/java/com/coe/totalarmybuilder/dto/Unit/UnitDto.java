package com.coe.totalarmybuilder.dto.Unit;
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

    public int Id;
    public String Name;
    public int Cost;
    public int AvatarId;

    //public List<CompositionDto> Compositions;
    //public List<FactionDto> Factions;
}
