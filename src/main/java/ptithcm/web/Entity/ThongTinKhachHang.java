package ptithcm.web.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "ThongTinKhachHang")
public class ThongTinKhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "nguoiDungId", unique = true)
    private NguoiDung nguoiDung;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "diaChi")
    private String diaChi;
    
    @Column(name = "soDienThoai")
    private String soDienThoai;

    // Getters and setters
}
