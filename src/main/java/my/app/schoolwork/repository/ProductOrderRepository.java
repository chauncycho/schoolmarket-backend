package my.app.schoolwork.repository;

import my.app.schoolwork.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer> {
    @Query(value="select * from product_order where user_id = :userId",nativeQuery=true)
    List<ProductOrder> findAllByUserId(@Param("userId") Integer userId);
}
