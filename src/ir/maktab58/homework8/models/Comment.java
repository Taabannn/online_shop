package ir.maktab58.homework8.models;

import ir.maktab58.homework8.models.products.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@ToString
public class Comment {
    private int id;
    private Customer costumer;
    private Product product;
    private String comment;

    @Builder
    public Comment(int id, Customer costumer, Product product, String comment) {
        this.id = id;
        this.costumer = costumer;
        this.product = product;
        this.comment = comment;
    }
}
