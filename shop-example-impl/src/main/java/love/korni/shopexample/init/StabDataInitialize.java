package love.korni.shopexample.init;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.service.CoffeeService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * StabDataInitialize
 *
 * @author Sergei_Konilov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StabDataInitialize implements InitializerService {

    private final CoffeeService coffeeService;
    private final Random random = new Random(System.currentTimeMillis());

    @Override
    public void initialize() {
        boolean empty = coffeeService.findAll().isEmpty();
        if (empty) {
            List<Coffee> coffees = List.of(
                    createCoffee("Americano", "classpath:static/coffee/Americano.jpg"),
                    createCoffee("Latte", "classpath:static/coffee/Latte.jpg"),
                    createCoffee("Espresso", "classpath:static/coffee/Espresso.jpg")
            );
            coffees.forEach(coffeeService::create);
        }
    }

    @SneakyThrows
    private Coffee createCoffee(String name, String imgurl) {
        log.info("Creating coffee: {}", name);
        double cost = Math.round(random.nextDouble(100f, 200f) * 100.0) / 100.0;
        return new Coffee().setCoffeeName(name).setCost(cost).setImg(imgurl);
    }
}
