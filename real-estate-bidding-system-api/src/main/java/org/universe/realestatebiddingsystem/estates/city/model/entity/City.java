package org.universe.realestatebiddingsystem.estates.city.model.entity;

import lombok.Data;

import javax.persistence.*;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.CITIES;

@Entity
@Table(name = CITIES)
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String code;
}
