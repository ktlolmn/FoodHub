package ptithcm.web.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DonHang")
public class DonHang {
	
    public DonHang() {
		super();
	}

	public DonHang(Long id, NguoiDung nguoiDung, LocalDateTime ngayTao, String trangThai) {
		super();
		this.id = id;
		this.nguoiDung = nguoiDung;
		this.ngayTao = ngayTao;
		this.trangThai = trangThai;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;
    
    @Column(name = "ngayTao")
    private LocalDateTime ngayTao;
    
    @Column(name = "trangThai", columnDefinition = "nvarchar(225)")
    private String trangThai;

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

	public LocalDateTime getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDateTime ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
}
