package ptithcm.web.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DonHang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;
    
    @Column(name = "ngayTao")
    private LocalDateTime ngayTao;
    
    @Column(name = "trangThai")
    private String trangThai;

    // Getters and setters
}
