package love.korni.shopexample.init;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.service.CoffeeService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * StabDataInitialize
 *
 * @author Sergei_Konilov
 */
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
                    createCoffee("Americano", "https://denewlanmarkhotel.com/wp-content/uploads/2020/05/americana-coffee.jpg"),
                    createCoffee("Latte", "https://st2.depositphotos.com/5355656/7824/i/950/depositphotos_78249960-stock-photo-hot-cafe-latte-and-coffee.jpg"),
                    createCoffee("Espresso", "https://www.acouplecooks.com/wp-content/uploads/2020/09/Latte-Art-066s.jpg")
            );
            coffees.forEach(coffeeService::create);
        }
    }

    @SneakyThrows
    private Coffee createCoffee(String name, String imgurl) {
        double cost = Math.round(random.nextDouble(100f, 200f) * 100.0) / 100.0;
        byte[] fileContent = IOUtils.toByteArray(new URL(imgurl));
        return new Coffee().setCoffeeName(name).setCost(cost).setImg(fileContent);
    }
}
