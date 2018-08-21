package org.universe.realestatebiddingsystem.estates.estate.model.entity;

import lombok.Data;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Entity
@Table(name = ESTATES)
@Data
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String action;

    @NotBlank
    @Column(nullable = false)
    private String type;

    @NotBlank
    @Column(nullable = false)
    private String city;

    @Min(1)
    @Column(nullable = false)
    private int area;

    @NotBlank
    @Column(nullable = false)
    private String image;

    private String additionalInfo;

//    cascade = CascadeType.PERSIST
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "estates_peculiarities", joinColumns = {
            @JoinColumn(name = "estate_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "peculariarity_id",
                    nullable = false, updatable = false)})
    private Set<Peculiarity> peculiarities;

    public void addPeculiarity(Peculiarity p) {
        this.peculiarities.add(p);
    }
}
