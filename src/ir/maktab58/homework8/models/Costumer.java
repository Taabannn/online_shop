package ir.maktab58.homework8.models;

import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Costumer {
    private int id;
    private String fullName;
    private String username;
    private String password;
    private long nationalCode;
    private int initialBalance;
    private int birthYear;

    public Costumer(int id, String fullName, String username, String password, long nationalCode, int balance, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.nationalCode = nationalCode;
        this.birthYear = birthYear;
        this.initialBalance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(int initialBalance) {
        this.initialBalance = initialBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costumer costumer = (Costumer) o;
        return nationalCode == costumer.nationalCode && birthYear == costumer.birthYear && fullName.equals(costumer.fullName) && username.equals(costumer.username) && password.equals(costumer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, username, password, nationalCode, birthYear);
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nationalCode=" + nationalCode +
                ", initialBalance=" + initialBalance +
                ", birthYear=" + birthYear +
                '}';
    }
}
