package ir.maktab58.onlineshop.models.products.readingItems;

import ir.maktab58.onlineshop.enumation.MagazineType;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorValue("magazine")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Magazine extends ReadingItems {
    @Enumerated(value = EnumType.STRING)
    private MagazineType magazineType;
    private String subject;

    @Builder(setterPrefix = "with")
    public Magazine(int id, String productName, long price, int count, String publisherName, MagazineType magazineType, String subject) {
        super(id, productName, price, count, publisherName);
        this.magazineType = magazineType;
        this.subject = subject;
    }
}
