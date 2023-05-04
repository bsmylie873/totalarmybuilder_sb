package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "lords")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LordUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "unit_id")
    private Unit unit;

}
