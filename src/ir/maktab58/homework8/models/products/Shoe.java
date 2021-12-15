package ir.maktab58.homework8.models.products;

import ir.maktab58.homework8.enumation.ShoeType;
import ir.maktab58.homework8.enumation.ShoeTypesInGeneral;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorValue("shoe")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Shoe extends Product {
    private String model;
    private int size;
    private String manufacturer;
    @Enumerated(value = EnumType.STRING)
    private ShoeTypesInGeneral shoeTypesInGeneral;
    @Enumerated(value = EnumType.STRING)
    private ShoeType shoeType;

    @Builder(setterPrefix = "with")
    public Shoe(int id, String productName, long price, int count, String model, int size, String manufacturer, ShoeTypesInGeneral shoeTypesInGeneral, ShoeType shoeType) {
        super(id, productName, price, count);
        this.model = model;
        this.size = size;
        this.manufacturer = manufacturer;
        this.shoeTypesInGeneral = shoeTypesInGeneral;
        this.shoeType = shoeType;
    }
}
