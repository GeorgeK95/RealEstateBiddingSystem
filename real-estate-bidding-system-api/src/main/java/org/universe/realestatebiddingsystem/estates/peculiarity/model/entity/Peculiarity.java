package org.universe.realestatebiddingsystem.estates.peculiarity.model.entity;

import lombok.Data;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;

import javax.persistence.*;

import java.util.Set;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.PECULIARITIES;

@Entity
@Table(name = PECULIARITIES)
@Data
public class Peculiarity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    /*@ManyToMany(mappedBy = "peculiarities")
    private Set<Estate> estates;*/
}
