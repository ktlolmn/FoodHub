package ptithcm.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptithcm.web.Entity.MonAn;
import ptithcm.web.Repository.MonAnRepository;
import java.util.List;

@Service
public class MonAnServiceImpl implements MonAnService {

    @Autowired
    private MonAnRepository monAnRepository;

    @Override
    public List<MonAn> getAllMonAn() {
        return monAnRepository.findAll();
    }

    @Override
    public MonAn getMonAnById(Long id) {
        return monAnRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveMonAn(MonAn monAn) {
        monAnRepository.save(monAn);
    }

    @Override
    @Transactional
    public void deleteMonAn(Long id) {
        monAnRepository.deleteById(id);
    }
}
