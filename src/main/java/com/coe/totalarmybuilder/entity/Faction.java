package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "factions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int Id;

    @Column(name = "name", nullable = false)
    private String Name;

    @OneToMany(mappedBy = "faction")
    private Set<UnitFaction> unitFactions;

}
