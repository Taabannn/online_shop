package ir.maktab58.homework8.exceptions;

/**
 * @author Taban Soleymani
 */
public class OnlineShopExceptions extends RuntimeException {
    private int errorCode;

    public OnlineShopExceptions(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
