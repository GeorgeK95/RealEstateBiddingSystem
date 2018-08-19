package org.universe.realestatebiddingsystem.estates.estate.model.entity;

import lombok.Data;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Set;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Entity
@Table(name = ADDS)
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

    private int floor;

    @NotBlank
    @Column(nullable = false)
    private int area;

    @NotBlank
    @Column(nullable = false)
    private String image;

    private String additionalInfo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "estates_peculiarity", joinColumns = {
            @JoinColumn(name = "estate_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "peculariarity_id",
                    nullable = false, updatable = false)})
    private Set<Peculiarity> peculiarities;
}
