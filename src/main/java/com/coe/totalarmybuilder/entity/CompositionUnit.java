package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "compositions_units")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CompositionUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int Id;

    @Column(name = "composition_id", insertable = false, updatable = false, nullable = false)
    private int CompositionId;

    @Column(name = "unit_id", insertable = false, updatable = false, nullable = false)
    private int UnitId;

    @ManyToOne
    private Composition composition;
    @ManyToOne
    private Unit unit;
}
