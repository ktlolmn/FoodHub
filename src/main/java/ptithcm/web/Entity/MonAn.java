package ptithcm.web.Entity;

import jakarta.persistence.*;
import java.math.*;

@Entity
@Table(name = "MonAn")
public class MonAn {
    
    public MonAn() {
        super();
    }

    public MonAn(Long id, String ten, String moTa, BigDecimal gia, byte[] img, String trangThai) {
        super();
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.gia = gia;
        this.img = img;
        this.trangThai = trangThai;
        this.base64Image = null; // Khởi tạo base64Image là null
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten")
    private String ten;
    
    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;
    
    @Column(name = "gia")
    private BigDecimal gia;
    
    @Lob // Sử dụng @Lob để lưu trữ dữ liệu lớn
    @Column(name = "img", columnDefinition = "VARBINARY(MAX)")
    private byte[] img;
    
    @Column(name = "trangThai")
    private String trangThai;
    
    // Thuộc tính để lưu base64 của ảnh
    @Transient // Đánh dấu không mapping với cột trong database
    private String base64Image;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
