package org.universe.realestatebiddingsystem.estates.type.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.type.model.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
