package ir.maktab58.onlineshop.models.products.electronicdevices;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorValue("television")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Television extends ElectronicDevices {
    private int width;

    @Builder(setterPrefix = "with")
    public Television(int id, String productName, long price, int count, String model, String manufacturer, int width) {
        super(id, productName, price, count, model, manufacturer);
        this.width = width;
    }
}
