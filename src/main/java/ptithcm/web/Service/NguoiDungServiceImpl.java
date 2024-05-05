package ptithcm.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ptithcm.web.Entity.NguoiDung;
import ptithcm.web.Repository.NguoiDungRepository;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public NguoiDung findByTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }
    @Override 
    @Transactional
    public void save(NguoiDung nguoiDung) {
    	nguoiDungRepository.save(nguoiDung);
    }
    @Override
    public Boolean existsByTenDangNhapAndThongTinKhachHang_Email(String tenDangNhap, String email) {
    	return nguoiDungRepository.existsByTenDangNhapAndThongTinKhachHang_Email(tenDangNhap, email);
    }
    
}
