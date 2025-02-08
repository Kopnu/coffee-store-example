package love.korni.shopexample.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

/**
 * Coffee
 *
 * @author Sergei_Konilov
 */
@Data
@Entity
@Accessors(chain = true)
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffee_default_seq")
    @SequenceGenerator(name = "coffee_default_seq", sequenceName = "coffee_default_seq", allocationSize = 1)
    private Long id;

    private String coffeeName;

    private Double cost;

    private String img;

}
