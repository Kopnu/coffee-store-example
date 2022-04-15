package love.korni.shopexample.resource;

import love.korni.shopexample.dto.CoffeeDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * CoffeeResource
 *
 * @author Sergei_Konilov
 */

@RequestMapping("/api/v1/coffee")
public interface CoffeeResource {

    @Operation(summary = "Add new type of coffee", security = @SecurityRequirement(name = "basicSecurity"))
    @PostMapping
    CoffeeDto create(@RequestBody CoffeeDto coffeeDto);

    @Operation(summary = "Get coffee by id")
    @GetMapping("/{id}")
    CoffeeDto getCoffee(@PathVariable Long id);

    @Operation(summary = "Get list of available coffees")
    @GetMapping
    List<CoffeeDto> getCoffees();

    @Operation(summary = "Get coffee image by id")
    @GetMapping(value = "/img/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getCoffeeImg(@PathVariable Long id);

    @Operation(summary = "Add coffee image by id", security = @SecurityRequirement(name = "basicSecurity"))
    @PostMapping(value = "/img/{id}", consumes = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<?> addCoffeeImg(@PathVariable Long id, @RequestBody byte[] img);

    @Operation(summary = "Update coffee", security = @SecurityRequirement(name = "basicSecurity"))
    @PutMapping
    CoffeeDto update(@RequestBody CoffeeDto coffeeDto);

    @Operation(summary = "Remove coffee by id", security = @SecurityRequirement(name = "basicSecurity"))
    @DeleteMapping("/{id}")
    ResponseEntity<?> remove(@PathVariable Long id);

}
