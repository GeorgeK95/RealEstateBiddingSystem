package org.universe.realestatebiddingsystem.estates.type.model.entity;


import lombok.Data;

import javax.persistence.*;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.TYPES;

@Entity
@Table(name = TYPES)
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String typeName;
}
