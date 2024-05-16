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
    
    @Column(name = "hoTen", columnDefinition = "nvarchar(225)")
    private String hoTen;
    
    @Column(name = "soDienThoai")
    private String soDienThoai;

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public ThongTinKhachHang(Long id, NguoiDung nguoiDung, String email, String soDienThoai, String hoTen) {
		super();
		this.id = id;
		this.nguoiDung = nguoiDung;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.hoTen = hoTen;
	}

	public ThongTinKhachHang() {
		super();
	}

    // Getters and setters
}
