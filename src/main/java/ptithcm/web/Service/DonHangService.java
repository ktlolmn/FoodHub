package ptithcm.web.Service;

import ptithcm.web.Entity.DonHang;
import java.util.List;

public interface DonHangService {

    List<DonHang> getAllDonHang();

    DonHang getDonHangById(Long id);

    DonHang saveDonHang(DonHang donHang);

    void deleteDonHang(Long id);

    List<DonHang> getDonHangByTrangThai(String trangThai);
    
    // Thêm phương thức mới để lưu đơn hàng và chi tiết đơn hàng
    void saveOrder(List<Long> foodIds, String address, Long userId, Double total);

    // Thêm phương thức mới để tìm đơn hàng theo tên đăng nhập
    List<DonHang> findAllByTenDangNhap(String tenDangNhap);
    
    List<DonHang> findAllByNguoiDungId(Long nguoiDungId);

    List<DonHang> findAllByNguoiDungIdAndTrangThai(Long idNguoiDung, String trangThai);
}
