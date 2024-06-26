package ptithcm.web.Entity;

import javax.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
	
    public NguoiDung(Long id, String tenDangNhap, String matKhau, VaiTro vaiTro) {
		super();
		this.id = id;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
	}

	public NguoiDung() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotEmpty(message = "Vui lòng nhập tên đăng nhập!")
    @Column(name = "tenDangNhap", unique = true)
    private String tenDangNhap;

	@NotEmpty(message = "Vui lòng nhập mật khẩu!")
    @Column(name = "matKhau")
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "vaiTroId")
    private VaiTro vaiTro;

    @OneToOne(mappedBy = "nguoiDung")
    private ThongTinKhachHang thongTinKhachHang;
    
    public ThongTinKhachHang getThongTinKhachHang() {
		return thongTinKhachHang;
	}

	public void setThongTinKhachHang(ThongTinKhachHang thongTinKhachHang) {
		this.thongTinKhachHang = thongTinKhachHang;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public VaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}

    // Getters and setters
}

