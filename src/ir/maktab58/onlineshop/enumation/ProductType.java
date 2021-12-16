package ir.maktab58.onlineshop.enumation;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@NoArgsConstructor
public enum ProductType {
    ELECTRONIC_DEVICES("electronic devices"),
    SHOE("shoe"),
    READING_ITEMS("reading items"),
    NOT_SET("not set");

    private @Getter String type;

    ProductType(String type) {
        this.type = type;
    }

    public ProductType getVal(String type){
        return switch (type.trim()) {
            case "electronic devices" -> ELECTRONIC_DEVICES;
            case "shoe" -> SHOE;
            case "reading items" -> READING_ITEMS;
            default -> NOT_SET;
        };
    }
}
