package org.universe.realestatebiddingsystem.estates.city.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.universe.realestatebiddingsystem.estates.city.model.entity.City;

@Transactional
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
