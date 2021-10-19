package ir.maktab58.homework8.models;

/**
 * @author Taban Soleymani
 */
public class Comments {
    private int id;
    private Costumer costumer;
    private Product product;
    private String comment;

    public Comments(int id, Costumer costumer, Product product, String comment) {
        this.id = id;
        this.costumer = costumer;
        this.product = product;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", costumer=" + costumer +
                ", product=" + product +
                ", comment='" + comment + '\'' +
                '}';
    }
}
