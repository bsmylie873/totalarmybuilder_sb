package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "accounts_compositions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AccountComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "account_id",  insertable = false,  updatable = false, nullable = false)
    private int accountId;
    @Column(name = "composition_id", insertable = false,  updatable = false, nullable = false)
    private int compositionId;

    @ManyToOne
    private Account account;
    @ManyToOne
    private Composition composition;

}
