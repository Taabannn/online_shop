package ir.maktab58.homework8.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class ReadingItems extends Product {
    private String publisherName;

    public ReadingItems(int id, String productName, long price, int count, String publisherName) {
        super(id, productName, price, count);
        this.publisherName = publisherName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingItems that = (ReadingItems) o;
        return name.equals(that.name) && publisherName.equals(that.publisherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, publisherName);
    }

    @Override
    public String toString() {
        return "ReadingItems{" +
                "name='" + name + '\'' +
                ", publisherName='" + publisherName + '\'' +
                "} " + super.toString();
    }
}
