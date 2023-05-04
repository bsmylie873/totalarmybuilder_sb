package com.coe.totalarmybuilder.dto.Faction;

import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FactionDto {
    public int id;
    public String name;

    public List<UnitDto> units;
}
