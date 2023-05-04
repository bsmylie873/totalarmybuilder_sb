package com.coe.totalarmybuilder.model.view.account;

import com.coe.totalarmybuilder.model.view.composition.CompositionView;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDetailView {

    @JsonProperty("id")
    private int id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("compositions")
    private List<CompositionView> compositions;

}
