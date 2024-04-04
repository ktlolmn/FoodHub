package ptithcm.web.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer orderID;
    
    @Column(name = "UserID")
    private Integer userID;
    
    @Column(name = "OrderDate", nullable = false)
    private Date orderDate;
    
    @Column(name = "TotalPrice", nullable = false)
    private Double totalPrice;
    
    @Column(name = "Status", nullable = false)
    private Integer status;
    
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", insertable = false, updatable = false)
    private User user;
    
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    
    // Constructors
    public Order() {
    }
    
    public Order(Integer userID, Date orderDate, Double totalPrice, Integer status) {
        this.userID = userID;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }
    
    // Getters and setters
    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
