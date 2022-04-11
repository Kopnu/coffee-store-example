package love.korni.shopexample.service.impl;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.repository.CoffeeRepository;
import love.korni.shopexample.service.CoffeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CoffeeServiceImpl
 *
 * @author Sergei_Konilov
 */
@Service
@RequiredArgsConstructor
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepository coffeeRepository;

    @Override
    public Coffee create(Coffee coffee) {
        coffee.setId(null);
        return coffeeRepository.save(coffee);
    }

    @Override
    public Coffee find(Long id) {
        return coffeeRepository.getById(id);
    }

    @Override
    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    @Override
    public Coffee update(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @Override
    public void delete(Long id) {
        coffeeRepository.deleteById(id);
    }
}
