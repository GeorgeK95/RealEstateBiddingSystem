package org.universe.realestatebiddingsystem.estates.estate.model.entity;

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

    private String name;

    private String code;
}
