package ptithcm.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.web.Entity.VaiTro;
import ptithcm.web.Repository.VaiTroRepository;

@Service
public class VaiTroServiceImpl implements VaiTroService {
	@Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public VaiTro findByTenVaiTro(String tenVaiTro) {
        return vaiTroRepository.findByTenVaiTro(tenVaiTro);
    }
}
