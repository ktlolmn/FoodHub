package ptithcm.web.Entity;
import jakarta.persistence.*;
@Entity
@Table(name = "ChiTietDonHang")
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "donHangId")
    private DonHang donHang;
    
    @ManyToOne
    @JoinColumn(name = "monAnId")
    private MonAn monAn;
    
    @Column(name = "soLuong")
    private int soLuong;

    // Getters and setters
}
