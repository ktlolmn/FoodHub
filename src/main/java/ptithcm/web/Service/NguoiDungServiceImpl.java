package ptithcm.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void save(NguoiDung nguoiDung) {
    	nguoiDungRepository.save(nguoiDung);
    }
    
}
