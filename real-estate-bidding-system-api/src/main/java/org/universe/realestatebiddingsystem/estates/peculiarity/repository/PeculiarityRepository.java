package org.universe.realestatebiddingsystem.estates.peculiarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;

@Repository
public interface PeculiarityRepository extends JpaRepository<Peculiarity, Long> {
}
