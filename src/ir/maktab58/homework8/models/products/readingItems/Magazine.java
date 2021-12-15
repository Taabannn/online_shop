package ir.maktab58.homework8.models.products.readingItems;

import ir.maktab58.homework8.enumation.MagazineType;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

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
    private MagazineType magazineType;
    private String subject;

    @Builder(setterPrefix = "with")
    public Magazine(int id, String productName, long price, int count, String publisherName, MagazineType magazineType, String subject) {
        super(id, productName, price, count, publisherName);
        this.magazineType = magazineType;
        this.subject = subject;
    }
}
