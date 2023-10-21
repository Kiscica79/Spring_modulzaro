package hu.example.spring_modulzaro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ordering")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderFrom;
    private String product;
    private double price;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person orderedBy;

    public Order() {}

    public Order(Integer id, String orderFrom, String product, double price) {
        this.id = id;
        this.orderFrom = orderFrom;
        this.product = product;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(Person orderedBy) {
        this.orderedBy = orderedBy;
    }
}
