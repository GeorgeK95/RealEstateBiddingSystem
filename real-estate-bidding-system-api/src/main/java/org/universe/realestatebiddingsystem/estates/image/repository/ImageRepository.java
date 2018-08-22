package org.universe.realestatebiddingsystem.estates.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.image.model.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
