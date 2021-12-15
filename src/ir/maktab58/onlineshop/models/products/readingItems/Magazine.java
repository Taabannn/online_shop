package ir.maktab58.onlineshop.models.products.readingItems;

import ir.maktab58.onlineshop.enumation.MagazineType;
import lombok.*;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Magazine extends ReadingItems {
    @Enumerated(value = EnumType.STRING)
    private MagazineType magazineType;
    private String subject;

    @Builder(setterPrefix = "with")
    public Magazine(String productName, long price, int count, String publisherName, MagazineType magazineType, String subject) {
        super(productName, price, count, publisherName);
        this.magazineType = magazineType;
        this.subject = subject;
    }
}
