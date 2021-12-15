package ir.maktab58.homework8.enumation;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@NoArgsConstructor
public enum ElectronicDevicesTypes {
    RADIO("radio"),
    TELEVISION("television"),
    NOT_SET("not set");


    private @Getter String type;

    ElectronicDevicesTypes(String type) {
        this.type = type;
    }

    public ElectronicDevicesTypes getVal(String type){
        return switch (type.trim()) {
            case "radio" -> RADIO;
            case "television" -> TELEVISION;
            default -> NOT_SET;
        };
    }
}
