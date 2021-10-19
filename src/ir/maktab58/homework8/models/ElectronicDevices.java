package ir.maktab58.homework8.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class ElectronicDevices extends Product {
    private String model;
    private String manufacturer;

    public ElectronicDevices(int id, String productName, long price, int count, String model, String manufacturer) {
        super(id, productName, price, count);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectronicDevices that = (ElectronicDevices) o;
        return Objects.equals(model, that.model) && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, manufacturer);
    }

    @Override
    public String toString() {
        return "ElectronicDevices{" +
                "model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                "} " + super.toString();
    }
}
