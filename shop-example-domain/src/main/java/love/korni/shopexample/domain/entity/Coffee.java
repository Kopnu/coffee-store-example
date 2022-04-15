package love.korni.shopexample.domain.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

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

    @Lob
    @Column(length = Integer.MAX_VALUE, columnDefinition = "BLOB")
    @ToString.Exclude
    private byte[] img;

}
