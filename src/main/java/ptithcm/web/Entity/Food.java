package ptithcm.web.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Foods")
public class Food {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FoodID")
    private Integer foodID;
    
    @Column(name = "Name", nullable = false)
    private String name;
    
    @Column(name = "Description")
    private String description;
    
    @Column(name = "Price", nullable = false)
    private Double price;
    
    @Column(name = "Image")
    private String image;
    
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;
    
    // Constructors
    public Food() {
    }
    
    public Food(String name, String description, Double price, String image, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
    }
    
    // Getters and setters
    public Integer getFoodID() {
        return foodID;
    }

    public void setFoodID(Integer foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
