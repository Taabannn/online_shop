package ir.maktab58.onlineshop.enumation;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@NoArgsConstructor
public enum ReadingItemsTypes {
    BOOK("book"),
    MAGAZINE("magazine"),
    NOT_SET("not set");

    private @Getter String type;

    ReadingItemsTypes(String type) {
        this.type = type;
    }

    public ReadingItemsTypes getVal(String type){
        return switch (type.trim()) {
            case "book" -> BOOK;
            case "magazine" -> MAGAZINE;
            default -> NOT_SET;
        };
    }
}
