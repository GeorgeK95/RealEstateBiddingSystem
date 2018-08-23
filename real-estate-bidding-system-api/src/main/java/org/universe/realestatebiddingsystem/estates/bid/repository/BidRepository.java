package org.universe.realestatebiddingsystem.estates.bid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.bid.model.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
}
