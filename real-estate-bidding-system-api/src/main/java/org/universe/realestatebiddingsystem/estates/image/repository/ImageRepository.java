
package org.universe.realestatebiddingsystem.estates.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.estates.image.model.entity.Image;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
//    @Transactional
//    @Modifying
//    @Query("delete from Image u where u.id in (:imgIdsToDelete)")
//    void deleteAllByIds(@Param("imgIdsToDelete") List<Long> imgIdsToDelete);

//    @Transactional
//    @Modifying
//    @Query("update Image i set i.estate = null where i.id in (:imgIdsToDelete)")
//    void proba(@Param("imgIdsToDelete") List<Long> imgIdsToDelete);
}
