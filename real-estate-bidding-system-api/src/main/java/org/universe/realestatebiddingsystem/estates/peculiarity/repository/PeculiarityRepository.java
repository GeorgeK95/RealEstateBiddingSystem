package org.universe.realestatebiddingsystem.estates.peculiarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;

import java.util.List;
import java.util.Set;

@Repository
public interface PeculiarityRepository extends JpaRepository<Peculiarity, Long> {

    @Query("select p from Peculiarity p where p.name in (:names)")
    Set<Peculiarity> findAllByName(@Param("names") Set<String> names);
}
