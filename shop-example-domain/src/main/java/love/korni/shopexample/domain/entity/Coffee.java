package love.korni.shopexample.domain.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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
    @GeneratedValue
    private Long id;

    private String coffeeName;

    private Double cost;

    @Lob
    @Column(length = Integer.MAX_VALUE, columnDefinition = "BLOB")
    @ToString.Exclude
    private byte[] img;

}
