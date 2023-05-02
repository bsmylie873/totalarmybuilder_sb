package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "units_factions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UnitFaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int Id;

    @Column(name = "unit_id", insertable = false, updatable = false, nullable = false)
    private int UnitId;

    @Column(name = "faction_id", insertable = false, updatable = false, nullable = false)
    private int FactionId;

    @ManyToOne
    private Unit unit;
    @ManyToOne
    private Faction faction;
}
