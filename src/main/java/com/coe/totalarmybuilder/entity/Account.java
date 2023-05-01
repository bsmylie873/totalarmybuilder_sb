package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "username", nullable = false)
    private String Username;
    @Column(name = "email", nullable = false)
    private String Email;
    @Column(name = "password", nullable = false)
    private String Password;
//    @Column(name = "compositions", nullable = true)
//    private List<Composition> Compositions;


}
