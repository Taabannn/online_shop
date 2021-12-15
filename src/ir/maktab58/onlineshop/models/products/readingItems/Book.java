package ir.maktab58.onlineshop.models.products.readingItems;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorValue("book")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Book extends ReadingItems {
    private String authorName;
    private String genre;

    @Builder(setterPrefix = "with")
    public Book(int id, String productName, long price, int count, String publisherName, String authorName, String genre) {
        super(id, productName, price, count, publisherName);
        this.authorName = authorName;
        this.genre = genre;
    }
}
