package ptithcm.web.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CustomerInfo")
public class CustomerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Integer customerID;
    
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;
    
    @Column(name = "FullName")
    private String fullName;
    
    @Column(name = "Phone")
    private String phone;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Address")
    private String address;
    
    // Constructors
    public CustomerInfo() {
    }
    
    public CustomerInfo(User user, String fullName, String phone, String email, String address) {
        this.user = user;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    
    // Getters and setters
    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
