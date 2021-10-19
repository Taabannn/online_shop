package ir.maktab58.homework8.enumation;

/**
 * @author Taban Soleymani
 */
public enum ProductType {
    ELECTRONIC_DEVICES("electronic devices"),
    SHOE("shoe"),
    READING_ITEMS("reading items"),
    NOT_SET("not set");

    private String type;

    ProductType(String type) {
        this.type = type;
    }

    public ProductType getVal(String type){
        switch (type.trim()){
            case "electronic devices":
                return ELECTRONIC_DEVICES;
            case "shoe":
                return SHOE;
            case "reading items":
                return READING_ITEMS;
            default:
                return NOT_SET;
        }
    }

    public String getType() {
        return type;
    }
}
