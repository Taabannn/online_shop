package ir.maktab58.homework8.enumation;

/**
 * @author Taban Soleymani
 */
public enum ShoeType {
    SPORT("sport"),
    CASUAL("casual"),
    NOT_SET(" not set");

    private String type;

    ShoeType(String type) {
        this.type = type;
    }

    public ShoeType getVal(String type){
        switch (type.trim()){
            case "sport":
                return SPORT;
            case "casual":
                return CASUAL;
            default:
                return NOT_SET;
        }
    }

    public String getType() {
        return type;
    }
}
