package ptithcm.web.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ptithcm.web.Entity.ChiTietDonHang;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Long> {
	List<ChiTietDonHang> findByDonHangId(Long donHangId);
}