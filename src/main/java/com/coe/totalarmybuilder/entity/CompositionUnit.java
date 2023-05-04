package com.coe.totalarmybuilder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int id;

    @Column(name = "composition_id", insertable = false, updatable = false, nullable = false)
    private int compositionId;

    @Column(name = "unit_id", insertable = false, updatable = false, nullable = false)
    private int unitId;

    @ManyToOne
    private Composition composition;
    @ManyToOne
    private Unit unit;
}
