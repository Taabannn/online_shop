package ir.maktab58.onlineshop.models.products.electronicdevices;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorValue("radio")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Radio extends ElectronicDevices {
    private boolean isPortable;

    @Builder(setterPrefix = "with")
    public Radio(int id, String productName, long price, int count, String model, String manufacturer, boolean isPortable) {
        super(id, productName, price, count, model, manufacturer);
        this.isPortable = isPortable;
    }
}
