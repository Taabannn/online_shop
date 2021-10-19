package ir.maktab58.homework8.enumation;

/**
 * @author Taban Soleymani
 */
public enum MagazineType {
    MONTHLY("monthly"),
    WEEKLY("weekly"),
    NOT_SET("not set");

    private String type;

    MagazineType(String type) {
        this.type = type;
    }

    public MagazineType getVal(String type){
        switch (type.trim()){
            case "monthly":
                return MONTHLY;
            case "weekly":
                return WEEKLY;
            default:
                return NOT_SET;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
