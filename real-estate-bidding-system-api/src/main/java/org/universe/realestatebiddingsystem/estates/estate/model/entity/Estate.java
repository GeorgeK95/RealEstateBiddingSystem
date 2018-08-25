package org.universe.realestatebiddingsystem.estates.estate.model.entity;

import lombok.Data;
import org.universe.realestatebiddingsystem.estates.bid.model.Bid;
import org.universe.realestatebiddingsystem.estates.image.model.entity.Image;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;
import org.universe.realestatebiddingsystem.user.model.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Min(AREA_MIN_VALUE)
    @Column(nullable = false)
    private Double area;

    @Min(PRICE_MIN_VALUE)
    @Column(nullable = false)
    private Double price;

    //    @Column(nullable = false)
    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cover_image_id")
    private Image coverImage;

    @ManyToOne
    private User author;

    @Column(nullable = false)
    @Size(min = ADDITIONAL_INFO_MIN_VALUE)
    private String additionalInfo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "estates_peculiarities", joinColumns = {
            @JoinColumn(name = "estate_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "peculariarity_id",
                    nullable = false, updatable = false)})
    private Set<Peculiarity> peculiarities;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.REMOVE)
    private List<Bid> bids = new ArrayList<>();

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public double getLastBidOrStartPrice() {
        int size = this.bids.size();

        return size != ZERO ? this.bids.get(size - 1).getPrice() : this.price;
    }

    public double getLastBid() {
        int size = this.bids.size();

        return size != ZERO ? this.bids.get(size - 1).getPrice() : ZERO;
    }
}
