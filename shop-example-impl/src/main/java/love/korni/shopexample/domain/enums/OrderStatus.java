package love.korni.shopexample.domain.enums;

/**
 * @author Sergei_Kornilov
 */
public enum OrderStatus {
    NEW,
    WAIT_PAYMENT,
    PAYMENT_ERROR,
    PAID_FOR,
    CLOSED,
    DELETED
}
