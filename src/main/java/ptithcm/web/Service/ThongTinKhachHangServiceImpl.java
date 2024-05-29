package ptithcm.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.web.Entity.ThongTinKhachHang;
import ptithcm.web.Repository.ThongTinKhachHangRepository;

@Service
public class ThongTinKhachHangServiceImpl implements ThongTinKhachHangService {

    @Autowired
    private ThongTinKhachHangRepository thongTinKhachHangRepository;

    @Override
    public ThongTinKhachHang getThongTinKhachHangByNguoiDungId(Long nguoiDungId) {
        return thongTinKhachHangRepository.findThongTinKhachHangByNguoiDungId(nguoiDungId);
    }

    @Override
    public boolean isEmailDuplicated(String email, Long nguoiDungId) {
        ThongTinKhachHang thongTinKhachHang = thongTinKhachHangRepository.findByEmail(email);
        if (thongTinKhachHang == null || thongTinKhachHang.getNguoiDung().getId().equals(nguoiDungId)) {
            return false; 
        } else {
            return true; 
        }
    }

    @Override
    public void saveThongTinKhachHang(ThongTinKhachHang thongTinKhachHang) {
        thongTinKhachHangRepository.save(thongTinKhachHang);
    }
}
