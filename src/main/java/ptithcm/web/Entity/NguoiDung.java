package ptithcm.web.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tenDangNhap", unique = true)
    private String tenDangNhap;
    
    @Column(name = "matKhau")
    private String matKhau;
    
    @ManyToOne
    @JoinColumn(name = "vaiTroId")
    private VaiTro vaiTro;

    // Getters and setters
}

