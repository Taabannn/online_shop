package ir.maktab58.onlineshop.models;

import ir.maktab58.onlineshop.models.products.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id")
    private Product product;
    private String comment;

    @Builder
    public Comment(int id, Customer customer, Product product, String comment) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.comment = comment;
    }
}
