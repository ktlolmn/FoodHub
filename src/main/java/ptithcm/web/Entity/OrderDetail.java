package ptithcm.web.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailID")
    private Integer orderDetailID;
    
    @ManyToOne
    @JoinColumn(name = "OrderID")
    private Order order;
    
    @Column(name = "FoodID")
    private Integer foodID;
    
    // Constructors
    public OrderDetail() {
    }
    
    public OrderDetail(Order order, Integer foodID) {
        this.order = order;
        this.foodID = foodID;
    }
    
    // Getters and setters
    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getFoodID() {
        return foodID;
    }

    public void setFoodID(Integer foodID) {
        this.foodID = foodID;
    }
}
