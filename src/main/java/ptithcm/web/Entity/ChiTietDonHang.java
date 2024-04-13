package ptithcm.web.Entity;
import jakarta.persistence.*;
@Entity
@Table(name = "ChiTietDonHang")
public class ChiTietDonHang {
	
    public ChiTietDonHang() {
		super();
	}

	public ChiTietDonHang(Long id, DonHang donHang, MonAn monAn, int soLuong) {
		super();
		this.id = id;
		this.donHang = donHang;
		this.monAn = monAn;
		this.soLuong = soLuong;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	public MonAn getMonAn() {
		return monAn;
	}

	public void setMonAn(MonAn monAn) {
		this.monAn = monAn;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

    // Getters and setters
}
