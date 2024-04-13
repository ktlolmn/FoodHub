package ptithcm.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.web.Entity.VaiTro;

public interface VaiTroRepository extends JpaRepository<VaiTro, Long> {
	VaiTro findByTenVaiTro(String tenVaiTro);
}
