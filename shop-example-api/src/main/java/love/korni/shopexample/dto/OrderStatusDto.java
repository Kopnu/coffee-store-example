package love.korni.shopexample.dto;

/**
 * @author Sergei_Kornilov
 */
public enum OrderStatusDto {
    NEW,
    WAIT_PAYMENT,
    PAYMENT_ERROR,
    PAID_FOR,
    CLOSED,
    DELETED
}
