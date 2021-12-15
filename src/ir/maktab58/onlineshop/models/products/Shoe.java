package ir.maktab58.onlineshop.models.products;

import ir.maktab58.onlineshop.enumation.ShoeType;
import ir.maktab58.onlineshop.enumation.ShoeTypesInGeneral;
import lombok.*;

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
public class Shoe extends Product {
    private String model;
    private int size;
    private String manufacturer;
    @Enumerated(value = EnumType.STRING)
    private ShoeTypesInGeneral shoeTypesInGeneral;
    @Enumerated(value = EnumType.STRING)
    private ShoeType shoeType;

    @Builder(setterPrefix = "with")
    public Shoe(String productName, long price, int count, String model, int size, String manufacturer, ShoeTypesInGeneral shoeTypesInGeneral, ShoeType shoeType) {
        super(productName, price, count);
        this.model = model;
        this.size = size;
        this.manufacturer = manufacturer;
        this.shoeTypesInGeneral = shoeTypesInGeneral;
        this.shoeType = shoeType;
    }
}
