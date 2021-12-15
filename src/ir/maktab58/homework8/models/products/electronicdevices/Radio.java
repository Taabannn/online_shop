package ir.maktab58.homework8.models.products.electronicdevices;

import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Radio extends ElectronicDevices {
    private boolean isPortable;

    public Radio(int id, String productName, long price, int count, String model, String manufacturer, boolean isPortable) {
        super(id, productName, price, count, model, manufacturer);
        this.isPortable = isPortable;
    }

    public boolean isPortable() {
        return isPortable;
    }

    public void setPortable(boolean portable) {
        isPortable = portable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Radio radio = (Radio) o;
        return isPortable == radio.isPortable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isPortable);
    }

    @Override
    public String toString() {
        return "Radio{" +
                "isPortable=" + isPortable +
                "} " + super.toString();
    }
}
