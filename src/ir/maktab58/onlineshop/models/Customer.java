package ir.maktab58.onlineshop.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    private String username;
    private String password;
    private String nationalCode;
    private long initialBalance;
    private int birthYear;

    @Builder(setterPrefix = "with")
    public Customer(String name, String family, String username, String password, String nationalCode, long initialBalance, int birthYear) {
        this.name = name;
        this.family = family;
        this.username = username;
        this.password = password;
        this.nationalCode = nationalCode;
        this.initialBalance = initialBalance;
        this.birthYear = birthYear;
    }
}
