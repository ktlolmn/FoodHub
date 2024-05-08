package ptithcm.web.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptithcm.web.Entity.MonAn;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Long> {
	List<MonAn> getMonAnByTen(String ten);
}
