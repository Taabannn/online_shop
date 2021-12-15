package ir.maktab58.homework8.models.products.electronicdevices;

import ir.maktab58.homework8.models.products.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class ElectronicDevices extends Product {
    private String model;
    private String manufacturer;

    public ElectronicDevices(int id, String productName, long price, int count, String model, String manufacturer) {
        super(id, productName, price, count);
        this.model = model;
        this.manufacturer = manufacturer;
    }
}
