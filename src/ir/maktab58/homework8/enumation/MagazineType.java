package ir.maktab58.homework8.enumation;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@NoArgsConstructor
public enum MagazineType {
    MONTHLY("monthly"),
    WEEKLY("weekly"),
    NOT_SET("not set");

    private @Getter String type;

    MagazineType(String type) {
        this.type = type;
    }

    public MagazineType getVal(String type){
        return switch (type.trim()) {
            case "monthly" -> MONTHLY;
            case "weekly" -> WEEKLY;
            default -> NOT_SET;
        };
    }
}
