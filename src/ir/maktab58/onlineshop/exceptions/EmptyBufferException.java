package ir.maktab58.onlineshop.exceptions;

/**
 * @author Taban Soleymani
 */
public class EmptyBufferException extends OnlineShopExceptions {
    public EmptyBufferException(String message, int errorCode) {
        super(message, errorCode);
    }
}
