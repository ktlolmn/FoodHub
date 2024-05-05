package ptithcm.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.web.Entity.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    NguoiDung findByTenDangNhap(String tenDangNhap);
    Boolean existsByTenDangNhapAndThongTinKhachHang_Email(String tenDangNhap, String email);
}
