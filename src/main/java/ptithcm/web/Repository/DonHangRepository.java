package ptithcm.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.web.Entity.DonHang;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    // Thêm các phương thức tùy chỉnh nếu cần
}
