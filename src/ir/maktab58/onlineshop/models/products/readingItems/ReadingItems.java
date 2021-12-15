package ir.maktab58.onlineshop.models.products.readingItems;

import ir.maktab58.onlineshop.models.products.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorColumn(name = "readingItem_type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("reading-item")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class ReadingItems extends Product {
    private String publisherName;

    public ReadingItems(int id, String productName, long price, int count, String publisherName) {
        super(id, productName, price, count);
        this.publisherName = publisherName;
    }
}
