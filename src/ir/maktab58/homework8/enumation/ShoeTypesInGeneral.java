package ir.maktab58.homework8.enumation;

/**
 * @author Taban Soleymani
 */
public enum ShoeTypesInGeneral {
    MASCULINE("masculine"),
    FEMININE("feminine"),
    NOT_SET("not set");

    private String type;

    ShoeTypesInGeneral(String type) {
        this.type = type;
    }

    public ShoeTypesInGeneral getVal(String type){
        switch (type.trim()){
            case "masculine":
                return MASCULINE;
            case "feminine":
                return FEMININE;
            default:
                return NOT_SET;
        }
    }

    public String getType() {
        return type;
    }
}
