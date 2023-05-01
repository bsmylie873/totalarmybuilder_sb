package com.coe.totalarmybuilder.dto.Composition;

import com.coe.totalarmybuilder.dto.Account.AccountDto;
import com.coe.totalarmybuilder.dto.Unit.UnitDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCompositionDto {
    public String Name;
    public String BattleType;
    public int FactionId;
    public int AvatarId;
    public int Budget;
    public Date DateCreated;
    public int Wins;
    public int Losses;
    public List<AccountDto> Accounts;
    public List<UnitDto> Units;
}
