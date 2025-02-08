package love.korni.shopexample.resource.impl;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.dto.CoffeeDto;
import love.korni.shopexample.mapper.CoffeeMapper;
import love.korni.shopexample.resource.CoffeeResource;
import love.korni.shopexample.service.CoffeeService;
import love.korni.shopexample.service.SecurityService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final SecurityService securityService;
    private final ResourceLoader resourceLoader;
    private final CoffeeMapper mapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CoffeeDto create(CoffeeDto coffeeDto) {
        securityService.checkIsAdmin();
        Coffee coffee = mapper.toEntity(coffeeDto);
        return mapper.toDto(coffeeService.create(coffee));
    }

    @Override
    public CoffeeDto getCoffee(Long id) {
        return mapper.toDto(coffeeService.find(id));
    }

    @Override
    public List<CoffeeDto> getCoffees() {
        return coffeeService.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @SneakyThrows
    public ResponseEntity<byte[]> getCoffeeImg(Long id) {
        Coffee coffee = coffeeService.find(id);
        Resource resource = resourceLoader.getResource(coffee.getImg());
        return ResponseEntity.ok().body(IOUtils.toByteArray(resource.getInputStream()));
    }

    @Override
    @SneakyThrows
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCoffeeImg(Long id, MultipartFile file) {
        securityService.checkIsAdmin();
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Файл пустой");
        }
        Coffee coffee = coffeeService.find(id);
        File saveFile = saveFile(file);
        coffee.setImg("file:" + saveFile.getAbsolutePath());
        coffeeService.update(coffee);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CoffeeDto update(CoffeeDto coffeeDto) {
        securityService.checkIsAdmin();
        Coffee coffee = mapper.toEntity(coffeeDto);
        return mapper.toDto(coffeeService.update(coffee));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> remove(Long id) {
        securityService.checkIsAdmin();
        coffeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    private Path getStorageDirectory() {
        Path storageDirectory = Paths.get("./uploads");
        File directory = storageDirectory.toFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return storageDirectory;
    }

    private File saveFile(MultipartFile file) throws IOException {
        File savedFile = new File(getStorageDirectory().toFile(), file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(savedFile)) {
            fos.write(file.getBytes());
        }
        return savedFile;
    }

}
