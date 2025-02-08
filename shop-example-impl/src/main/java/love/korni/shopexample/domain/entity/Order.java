package love.korni.shopexample.domain.entity;

import love.korni.shopexample.domain.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_default_seq")
    @SequenceGenerator(name = "orders_default_seq", sequenceName = "orders_default_seq", allocationSize = 1)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coffee> coffees;

    private String userName;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

}
