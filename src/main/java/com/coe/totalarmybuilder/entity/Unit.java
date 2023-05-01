package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "units")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "cost", nullable = false)
    private int Cost;

    @Column(name = "avatarId", nullable = false)
    private int AvatarId;

   /* @Column(name = "compositions", nullable = false)
    private List<CompositionDto> Compositions;

    @Column(name = "factions", nullable = false)
    private List<FactionDto> Factions;*/

}
