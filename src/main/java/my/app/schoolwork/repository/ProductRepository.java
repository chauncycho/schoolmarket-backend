package my.app.schoolwork.repository;

import my.app.schoolwork.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value="select * from product where state = :state",nativeQuery=true)
    List<Product> findByState(@Param("state") String state);
}
