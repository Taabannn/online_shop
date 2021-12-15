package ir.maktab58.onlineshop.models.products.electronicdevices;

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
public abstract class ElectronicDevices extends Product {
    private String model;
    private String manufacturer;

    public ElectronicDevices(String productName, long price, int count, String model, String manufacturer) {
        super(productName, price, count);
        this.model = model;
        this.manufacturer = manufacturer;
    }
}
