package ptithcm.web.Entity;

import jakarta.persistence.*;
import java.math.*;

@Entity
@Table(name = "MonAn")
public class MonAn {
    
    public MonAn() {
        super();
    }

    public MonAn(Long id, String ten, Long gia, byte[] img, Boolean trangThai) {
        super();
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.img = img;
        this.trangThai = trangThai;
        this.base64Image = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten", columnDefinition = "nvarchar(225)")
    private String ten;
    
    @Column(name = "gia")
    private Long gia;
    
    @Column(name = "img", columnDefinition = "VARBINARY(MAX)")
    private byte[] img;
    
    @Column(name = "trangThai")
    private Boolean trangThai;
    
    private String base64Image;
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


    public Long getGia() {
        return gia;
    }

    public void setGia(Long gia) {
        this.gia = gia;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
