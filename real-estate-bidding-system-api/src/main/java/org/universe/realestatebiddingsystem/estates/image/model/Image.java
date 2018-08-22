package org.universe.realestatebiddingsystem.estates.image.model;

import lombok.Data;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;

import javax.persistence.*;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.IMAGES;

@Entity
@Table(name = IMAGES)
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @ManyToOne
//    @JoinColumn(name = "estate_id")
    private Estate estate;

    public Image() {
    }

    public Image(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
