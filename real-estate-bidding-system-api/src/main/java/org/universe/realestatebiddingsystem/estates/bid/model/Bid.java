package org.universe.realestatebiddingsystem.estates.bid.model;


import lombok.Data;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;
import org.universe.realestatebiddingsystem.user.model.entity.User;

import javax.persistence.*;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.BIDS;

@Entity
@Table(name = BIDS)
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User author;

    @ManyToOne
    private Estate estate;
}
