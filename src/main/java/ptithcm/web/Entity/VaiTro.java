package ptithcm.web.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "VaiTro")
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tenVaiTro")
    private String tenVaiTro;

    // Getters and setters
}
