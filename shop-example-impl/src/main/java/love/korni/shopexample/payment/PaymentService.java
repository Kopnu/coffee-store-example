package love.korni.shopexample.payment;

import love.korni.shopexample.domain.entity.Order;
import love.korni.shopexample.repository.OrderRepository;
import love.korni.shopexample.service.OrderService;
import love.korni.shopexample.util.DateUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * PaymentScheduler
 *
 * @author Sergei_Konilov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TaskScheduler scheduler;
    private final OrderRepository orderRepository;
    private final Random random = new Random(System.currentTimeMillis());

    @Async
    public void payment(Order order) {
        Date startTime = getRandomTime();
        log.debug("Schedule payment for order with id {}. Start time: {}", order.getId(), startTime);
        scheduler.schedule(() -> {
            if (Order.OrderStatus.WAIT_PAYMENT == order.getStatus()) {
                int nextInt = random.nextInt(100);
                if (nextInt > 20) {
                    order.setStatus(Order.OrderStatus.PAID_FOR);
                } else {
                    order.setStatus(Order.OrderStatus.PAYMENT_ERROR);
                }
                orderRepository.save(order);
                log.debug("Schedule payment for order with id {} ended.", order.getId());
                delivery(order);
            }
        }, startTime);
    }

    @Async
    public void delivery(Order order) {
        Date startTime = getRandomTime();
        log.debug("Schedule delivery for order with id {}. Start time: {}", order.getId(), startTime);
        scheduler.schedule(() -> {
            if (Order.OrderStatus.PAID_FOR == order.getStatus()) {
                order.setStatus(Order.OrderStatus.CLOSED);
                orderRepository.save(order);
                log.debug("Schedule delivery for order with id {} ended.", order.getId());
            }
        }, startTime);
    }

    private Date getRandomTime() {
        int seconds = random.nextInt(30) + 31;
        LocalDateTime time = LocalDateTime.now().plusSeconds(seconds);
        return DateUtils.convertToDate(time);
    }
}
