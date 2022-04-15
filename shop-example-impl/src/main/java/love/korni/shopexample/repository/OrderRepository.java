package love.korni.shopexample.repository;

import love.korni.shopexample.domain.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderRepository
 *
 * @author Sergei_Konilov
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserName(String userName);

}
