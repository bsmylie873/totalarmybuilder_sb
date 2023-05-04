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
public class UpdateAccountDto {
    private String username;
    private String email;
    private String password;
    private List<CompositionDto> compositions;
}
