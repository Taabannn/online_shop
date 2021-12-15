package ir.maktab58.homework8.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

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
    private String fullName;
    private String username;
    private String password;
    private long nationalCode;
    private long initialBalance;
    private int birthYear;
    @OneToMany(mappedBy = "customer")
    private List<Comment> commentList = new ArrayList<>();
    @OneToOne
    private Cart cart;

    @Builder
    public Customer(int id, String fullName, String username, String password, long nationalCode, long balance, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.nationalCode = nationalCode;
        this.birthYear = birthYear;
        this.initialBalance = balance;
    }
}
