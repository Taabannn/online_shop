package ir.maktab58.homework8.enumation;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@NoArgsConstructor
public enum ShoeTypesInGeneral {
    MASCULINE("masculine"),
    FEMININE("feminine"),
    NOT_SET("not set");

    private @Getter String type;

    ShoeTypesInGeneral(String type) {
        this.type = type;
    }

    public ShoeTypesInGeneral getVal(String type){
        return switch (type.trim()) {
            case "masculine" -> MASCULINE;
            case "feminine" -> FEMININE;
            default -> NOT_SET;
        };
    }
}
