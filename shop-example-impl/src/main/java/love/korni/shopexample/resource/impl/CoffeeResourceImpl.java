package love.korni.shopexample.resource.impl;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.dto.CoffeeDto;
import love.korni.shopexample.resource.CoffeeResource;
import love.korni.shopexample.service.CoffeeService;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CoffeeResourceImpl
 *
 * @author Sergei_Konilov
 */
@RestController
@RequiredArgsConstructor
public class CoffeeResourceImpl implements CoffeeResource {

    private final CoffeeService coffeeService;
    private final MapperFacade mapperFacade;

    @Override
    public CoffeeDto create(CoffeeDto coffeeDto) {
        Coffee coffee = mapperFacade.map(coffeeDto, Coffee.class);
        return mapperFacade.map(coffeeService.create(coffee), CoffeeDto.class);
    }

    @Override
    public CoffeeDto getCoffee(Long id) {
        return mapperFacade.map(coffeeService.find(id), CoffeeDto.class);
    }

    @Override
    public List<CoffeeDto> getCoffees() {
        return mapperFacade.mapAsList(coffeeService.findAll(), CoffeeDto.class);
    }

    @Override
    public ResponseEntity<byte[]> getCoffeeImg(Long id) {
        Coffee coffee = coffeeService.find(id);
        return ResponseEntity.ok().body(coffee.getImg());
    }

    @Override
    public ResponseEntity<?> addCoffeeImg(Long id, byte[] img) {
        Coffee coffee = coffeeService.find(id);
        coffee.setImg(img);
        coffeeService.update(coffee);
        return ResponseEntity.ok().build();
    }

    @Override
    public CoffeeDto update(CoffeeDto coffeeDto) {
        Coffee coffee = mapperFacade.map(coffeeDto, Coffee.class);
        return mapperFacade.map(coffeeService.update(coffee), CoffeeDto.class);
    }

    @Override
    public ResponseEntity<?> remove(Long id) {
        coffeeService.delete(id);
        return ResponseEntity.ok().build();
    }


}
