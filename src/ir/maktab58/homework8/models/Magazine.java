package ir.maktab58.homework8.models;

import ir.maktab58.homework8.enumation.MagazineType;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Magazine extends ReadingItems {
    private MagazineType magazineType;
    private String subject;

    public Magazine(int id, String productName, long price, int count, String publisherName, MagazineType magazineType, String subject) {
        super(id, productName, price, count, publisherName);
        this.magazineType = magazineType;
        this.subject = subject;
    }

    public MagazineType getMagazineType() {
        return magazineType;
    }

    public void setMagazineType(MagazineType magazineType) {
        this.magazineType = magazineType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return magazineType == magazine.magazineType && subject.equals(magazine.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), magazineType, subject);
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "magazineType=" + magazineType +
                ", type='" + subject + '\'' +
                "} " + super.toString();
    }
}
