package ir.maktab58.homework8.enumation;

/**
 * @author Taban Soleymani
 */
public enum ElectronicDevicesTypes {
    RADIO("radio"),
    TELEVISION("television"),
    NOT_SET("not set");


    private String type;

    ElectronicDevicesTypes(String type) {
        this.type = type;
    }

    public ElectronicDevicesTypes getVal(String type){
        switch (type.trim()){
            case "radio":
                return RADIO;
            case "television":
                return TELEVISION;
            default:
                return NOT_SET;
        }
    }

    public String getType() {
        return type;
    }
}
