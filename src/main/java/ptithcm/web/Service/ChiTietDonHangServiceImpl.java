package ptithcm.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import ptithcm.web.Entity.ChiTietDonHang;
import ptithcm.web.Repository.ChiTietDonHangRepository;

@Service
public class ChiTietDonHangServiceImpl implements ChiTietDonHangService {

    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;

    @Override
    public List<ChiTietDonHang> getChiTietDonHangByDonHangId(Long donHangId) {
        return chiTietDonHangRepository.findByDonHangId(donHangId);
    }
    @Override
    @Transactional
    public void deleteByDonHangId(Long donHangId) {
    	chiTietDonHangRepository.deleteByDonHangId(donHangId);
    }
}
