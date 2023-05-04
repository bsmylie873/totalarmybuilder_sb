package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "units")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private int cost;

    @Column(name = "avatarId", nullable = false)
    private int avatarId;

    @OneToMany(mappedBy = "unit")
    private List<UnitFaction> unitFactions;

    @OneToOne(mappedBy = "unit", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private LordUnit lordUnit;

    @OneToOne(mappedBy = "unit", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private HeroUnit heroUnit;
}
