package org.universe.realestatebiddingsystem.estates.estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long> {
}
