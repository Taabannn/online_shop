package ir.maktab58.homework8.models.products.electronicdevices;

import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Television extends ElectronicDevices {
    private int width;

    public Television(int id, String productName, long price, int count, String model, String manufacturer, int width) {
        super(id, productName, price, count, model, manufacturer);
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Television that = (Television) o;
        return width == that.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width);
    }

    @Override
    public String toString() {
        return "Television{" +
                "width=" + width +
                "} " + super.toString();
    }
}
