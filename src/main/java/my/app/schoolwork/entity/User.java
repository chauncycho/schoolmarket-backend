package my.app.schoolwork.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import my.app.schoolwork.util.MD5Encrypt;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "int")
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isManager;

    @Column
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user","product"})
    private List<ProductOrder> orders;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = MD5Encrypt.encrypt(password);
    }

    public boolean isTruePassword(String password){
        if (MD5Encrypt.encrypt(password).equals(this.password)){
            return true;
        } else {
            return false;
        }
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public List<ProductOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ProductOrder> orders) {
        this.orders = orders;
    }
}
