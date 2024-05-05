package ptithcm.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.web.Entity.ThongTinKhachHang;

public interface ThongTinKhachHangRepository extends JpaRepository<ThongTinKhachHang, Long> {
    // Các phương thức tùy chỉnh khác nếu cần
}
