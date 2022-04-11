package love.korni.shopexample.repository;

import love.korni.shopexample.domain.entity.Coffee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CoffeeRepository
 *
 * @author Sergei_Konilov
 */
@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
