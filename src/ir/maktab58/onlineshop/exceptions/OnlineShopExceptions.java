package ir.maktab58.onlineshop.exceptions;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Taban Soleymani
 */
public class OnlineShopExceptions extends RuntimeException {
    private final @Getter int errorCode;

    @Builder
    public OnlineShopExceptions(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
