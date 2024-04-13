package ptithcm.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.web.Entity.DonHang;
import ptithcm.web.Repository.DonHangRepository;
import java.util.List;

@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Override
    public List<DonHang> getAllDonHang() {
        return donHangRepository.findAll();
    }

    @Override
    public DonHang getDonHangById(Long id) {
        return donHangRepository.findById(id).orElse(null);
    }

    @Override
    public DonHang saveDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    @Override
    public void deleteDonHang(Long id) {
        donHangRepository.deleteById(id);
    }
}
