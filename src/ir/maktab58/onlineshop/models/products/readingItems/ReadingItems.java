package ir.maktab58.onlineshop.models.products.readingItems;

import ir.maktab58.onlineshop.models.products.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Taban Soleymani
 */
@Entity
@PrimaryKeyJoinColumn(name = "product_id")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class ReadingItems extends Product {
    private String publisherName;

    public ReadingItems(String productName, long price, int count, String publisherName) {
        super(productName, price, count);
        this.publisherName = publisherName;
    }
}
