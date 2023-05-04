package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int id;

    @Column(name = "unit_id", insertable = false, updatable = false, nullable = false)
    private int unitId;

    @Column(name = "faction_id", insertable = false, updatable = false, nullable = false)
    private int factionId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @ManyToOne
    private Faction faction;
}
