package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "factions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "name", nullable = false)
    private String Name;

//    @Column(name = "units", nullable = false)
//    private List<Unit> Units;

}
