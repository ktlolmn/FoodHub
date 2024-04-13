package ptithcm.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptithcm.web.Entity.MonAn;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần
}
