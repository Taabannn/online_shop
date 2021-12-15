package ir.maktab58.onlineshop.models;

import ir.maktab58.onlineshop.models.products.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

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
    @OneToMany
    private ArrayList<Product> products;
}
