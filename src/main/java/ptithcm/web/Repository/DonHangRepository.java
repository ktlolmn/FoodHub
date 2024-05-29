package ptithcm.web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.web.Entity.DonHang;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByTrangThai(String trangThai);
    List<DonHang> findAllByNguoiDungTenDangNhap(String tenDangNhap);
    List<DonHang> findAllByNguoiDungId(Long nguoiDungId);
    List<DonHang> findAllByNguoiDungIdAndTrangThai(Long nguoiDungId, String trangThai);

}
