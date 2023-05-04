package com.coe.totalarmybuilder.dto.Composition;

import com.coe.totalarmybuilder.dto.Account.AccountDto;
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
public class UpdateCompositionDto {
    public String name;
    public String battleType;
    public int factionId;
    public int avatarId;
    public int budget;
    public int wins;
    public int losses;
    public List<AccountDto> accounts;
    public List<UnitDto> units;
}
