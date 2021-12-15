package ir.maktab58.onlineshop.models;

import ir.maktab58.onlineshop.models.products.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id")
    private List<Product> products = new ArrayList<>();
}
