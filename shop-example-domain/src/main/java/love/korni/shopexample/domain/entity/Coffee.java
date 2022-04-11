package love.korni.shopexample.domain.entity;

import lombok.Data;

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
public class Coffee {

    @Id
    @GeneratedValue
    private Long id;

    private String coffeeName;

    private Double cost;

    @Lob
    @Column(length = Integer.MAX_VALUE, columnDefinition = "BLOB")
    private byte[] img;

}
