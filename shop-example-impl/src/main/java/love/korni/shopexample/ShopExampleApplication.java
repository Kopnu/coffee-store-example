package love.korni.shopexample;

import love.korni.shopexample.init.InitializerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

import java.util.function.Consumer;

@Slf4j
@SpringBootApplication
public class ShopExampleApplication {

    //FYI: http://localhost:8080/swagger-ui/index.html#/

    public static void main(String[] args) {
        SpringApplication.run(ShopExampleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialize(ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();
        safeExecute(context, c -> {
            c.getBeansOfType(InitializerService.class).forEach((name, service) -> {
                log.trace("initialize() - {}", name);
                service.initialize();
            });
        });
        log.info("~~~~~~ Full initialize completed ~~~~~~");
    }

    private void safeExecute(ApplicationContext context, Consumer<ApplicationContext> contextConsumer) {
        try {
            contextConsumer.accept(context);
        } catch (Exception e) {
            log.error("Initialization completed with exception: ", e);
        }
    }

}
