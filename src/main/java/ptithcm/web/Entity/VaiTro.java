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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenVaiTro() {
		return tenVaiTro;
	}

	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
	}

    // Getters and setters
}
