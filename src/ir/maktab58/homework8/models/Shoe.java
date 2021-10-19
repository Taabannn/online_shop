package ir.maktab58.homework8.models;

import ir.maktab58.homework8.enumation.ShoeType;
import ir.maktab58.homework8.enumation.ShoeTypesInGeneral;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Shoe extends Product {
    private String model;
    private int size;
    private String manufacturer;
    private ShoeTypesInGeneral shoeTypesInGeneral;
    private ShoeType shoeType;

    public Shoe(int id, String productName, long price, int count, String model, int size, String manufacturer, ShoeTypesInGeneral shoeTypesInGeneral, ShoeType shoeType) {
        super(id, productName, price, count);
        this.model = model;
        this.size = size;
        this.manufacturer = manufacturer;
        this.shoeTypesInGeneral = shoeTypesInGeneral;
        this.shoeType = shoeType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ShoeTypesInGeneral getShoeTypesInGeneral() {
        return shoeTypesInGeneral;
    }

    public void setShoeTypesInGeneral(ShoeTypesInGeneral shoeTypesInGeneral) {
        this.shoeTypesInGeneral = shoeTypesInGeneral;
    }

    public ShoeType getShoeType() {
        return shoeType;
    }

    public void setShoeType(ShoeType shoeType) {
        this.shoeType = shoeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoe shoe = (Shoe) o;
        return size == shoe.size && model.equals(shoe.model) && manufacturer.equals(shoe.manufacturer) && shoeTypesInGeneral == shoe.shoeTypesInGeneral && shoeType == shoe.shoeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, size, manufacturer, shoeTypesInGeneral, shoeType);
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "model='" + model + '\'' +
                ", size=" + size +
                ", manufacturer='" + manufacturer + '\'' +
                ", shoeTypesInGeneral=" + shoeTypesInGeneral +
                ", shoeType=" + shoeType +
                "} " + super.toString();
    }
}
