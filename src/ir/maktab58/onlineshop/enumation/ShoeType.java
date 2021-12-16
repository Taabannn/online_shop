package ir.maktab58.onlineshop.enumation;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@NoArgsConstructor
public enum ShoeType {
    SPORT("sport"),
    CASUAL("casual"),
    NOT_SET(" not set");

    private @Getter String type;

    ShoeType(String type) {
        this.type = type;
    }

    public ShoeType getVal(String type){
        return switch (type.trim()) {
            case "sport" -> SPORT;
            case "casual" -> CASUAL;
            default -> NOT_SET;
        };
    }
}
