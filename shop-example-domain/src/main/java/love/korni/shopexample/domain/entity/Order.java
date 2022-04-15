package love.korni.shopexample.domain.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

/**
 * Order
 *
 * @author Sergei_Konilov
 */
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coffee> coffees;

    private String userName;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    public enum OrderStatus {
        NEW,
        WAIT_PAYMENT,
        PAYMENT_ERROR,
        PAID_FOR,
        CLOSED,
        DELETED;
    }

}
