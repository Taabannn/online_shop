package ir.maktab58.homework8.enumation;

/**
 * @author Taban Soleymani
 */
public enum ReadingItemsTypes {
    BOOK("book"),
    MAGAZINE("magazine"),
    NOT_SET("not set");

    private String type;

    ReadingItemsTypes(String type) {
        this.type = type;
    }

    public ReadingItemsTypes getVal(String type){
        switch (type.trim()){
            case "book":
                return BOOK;
            case "magazine":
                return MAGAZINE;
            default:
                return NOT_SET;
        }
    }

    public String getType() {
        return type;
    }
}
