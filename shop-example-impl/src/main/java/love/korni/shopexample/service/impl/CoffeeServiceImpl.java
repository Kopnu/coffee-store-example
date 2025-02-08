package love.korni.shopexample.service.impl;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.exception.ValidationException;
import love.korni.shopexample.repository.CoffeeRepository;
import love.korni.shopexample.service.CoffeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public List<Coffee> findAll(List<Long> ids) {
        return coffeeRepository.findAllById(ids);
    }

    @Override
    public Coffee update(Coffee coffee) {
        if (Objects.isNull(coffee.getId())) throw new ValidationException("Coffee id cannot be empty.");
        return coffeeRepository.save(coffee);
    }

    @Override
    public void delete(Long id) {
        coffeeRepository.deleteById(id);
    }
}
