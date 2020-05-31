package my.app.schoolwork.repository;


import my.app.schoolwork.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    @Query(value = "select * from image where random_name = :randomName limit 0,1",nativeQuery = true)
    Image findByRandomName(String randomName);

    @Query(value = "select * from image where url = :url",nativeQuery = true)
    List<Image> findListByUrl(@Param("url") String url);
}
