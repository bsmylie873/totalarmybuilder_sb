package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Table(name = "compositions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "battleType", nullable = false)
    private String battleType;

    @Column(name = "factionId", nullable = false)
    private int factionId;

    @Column(name = "avatarId", nullable = false)
    private int avatarId;

    @Column(name = "budget", nullable = false)
    private int budget;

    @Column(name = "dateCreated", nullable = false)
    private Date dateCreated;

    @Column(name = "wins", nullable = false)
    private int wins;

    @Column(name = "losses", nullable = false)
    private int losses;

    @OneToMany(mappedBy = "composition")
    private Set<AccountComposition> accountCompositions;

    @OneToMany(mappedBy = "composition")
    private Set<CompositionUnit> compositionUnits;
}
