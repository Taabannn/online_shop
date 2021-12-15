package ir.maktab58.onlineshop.exceptions;

import lombok.Builder;

/**
 * @author Taban Soleymani
 */
public class OnlineShopExceptions extends RuntimeException {
    private int errorCode;

    @Builder
    public OnlineShopExceptions(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
