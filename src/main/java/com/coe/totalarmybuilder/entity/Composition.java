package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "compositions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "battleType", nullable = false)
    private String BattleType;

    @Column(name = "factionId", nullable = false)
    private int FactionId;

    @Column(name = "avatarId", nullable = false)
    private int AvatarId;

    @Column(name = "budget", nullable = false)
    private int Budget;

    @Column(name = "dateCreated", nullable = false)
    private Date DateCreated;

    @Column(name = "wins", nullable = false)
    private int Wins;

    @Column(name = "losses", nullable = false)
    private int Losses;

//    @Column(name = "accounts", nullable = false)
//    private List<Account> Accounts;
//
//    @Column(name = "units", nullable = true)
//    private List<Unit> Units;

}
