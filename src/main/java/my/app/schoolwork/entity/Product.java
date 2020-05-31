package my.app.schoolwork.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "int")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String des;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "text")
    private String picturePath;

    @Column(nullable = false)
    private String state;//created examine release locked finished

    @Column
    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties({"user","product"})
    private List<ProductOrder> orders;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User founder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public List<ProductOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ProductOrder> orders) {
        this.orders = orders;
    }

    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }
}
