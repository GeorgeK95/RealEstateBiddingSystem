package org.universe.realestatebiddingsystem.estates.estate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;

import javax.transaction.Transactional;

import java.util.List;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.ID;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long> {
    String USER_ID = "userId";

    @Transactional
    @Modifying
    @Query("delete from Estate e where e.id = :id")
    void deleteBid(@Param(ID) Long id);

    @Query("select u.estates from User u where u.id = :userId")
    List<Estate> getEstatesByUserId(@Param(USER_ID) Long userId);

    @Query("select e from Bid b join b.estate e on e.id = b.estate.id where b.author.id = :userId group by e.id")
    List<Estate> getBidsEstatesByUserId(@Param(USER_ID) Long userId);

    @Query("select e from Estate e where e.city like :city")
    Page<Estate> findAllByCity(Pageable pageable, @Param("city") String city);

    @Query("select e from Estate e where e.area <= :area")
    Page<Estate> findAllByArea(Pageable pageable, @Param("area") Double area);

    @Query("select e from Estate e where e.price <= :price")
    Page<Estate> findAllByPrice(Pageable pageable, @Param("price") Double price);
}
