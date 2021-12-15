package ir.maktab58.onlineshop.models.products.electronicdevices;

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
@DiscriminatorColumn(name = "electronicDevice_type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("electronic-device")
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
