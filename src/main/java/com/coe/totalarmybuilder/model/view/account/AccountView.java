package com.coe.totalarmybuilder.model.view.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountView {

    @JsonProperty("username")
    private String username;

    @JsonProperty("Email")
    private String email;


}
