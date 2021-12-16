package ir.maktab58.onlineshop.models.products.electronicdevices;

import lombok.*;

import javax.persistence.Entity;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Radio extends ElectronicDevices {
    private boolean isPortable;

    @Builder(setterPrefix = "with")

    public Radio(String productName, long price, int count, String model, String manufacturer, boolean isPortable) {
        super(productName, price, count, model, manufacturer);
        this.isPortable = isPortable;
    }
}
