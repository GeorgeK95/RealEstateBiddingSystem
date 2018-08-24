package org.universe.realestatebiddingsystem.estates.bid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.bid.model.Bid;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    String USER_ID = "userId";

    @Query("select u.bids from User u where u.id =:userId")
    List<Bid> getBidsByUserId(@Param(USER_ID) Long userId);
}
