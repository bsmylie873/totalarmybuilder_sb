package com.coe.totalarmybuilder.dto.Account;

import com.coe.totalarmybuilder.dto.Composition.CompositionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAccountDto {
    private String Username;
    private String Email;
    private String Password;
    private List<CompositionDto> Compositions;
}
