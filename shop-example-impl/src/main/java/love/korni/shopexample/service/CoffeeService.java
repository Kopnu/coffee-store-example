package love.korni.shopexample.service;

import love.korni.shopexample.domain.entity.Coffee;

import java.util.List;

/**
 * CoffeeService
 *
 * @author Sergei_Konilov
 */
public interface CoffeeService {

    Coffee create(Coffee coffee);

    Coffee find(Long id);

    List<Coffee> findAll();

    Coffee update(Coffee coffee);

    void delete(Long id);

}
