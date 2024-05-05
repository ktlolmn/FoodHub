package ptithcm.web.Service;

import ptithcm.web.Entity.NguoiDung;

public interface NguoiDungService {
    NguoiDung findByTenDangNhap(String tenDangNhap);
    void save(NguoiDung nguoiDung);
    Boolean existsByTenDangNhapAndThongTinKhachHang_Email(String tenDangNhap, String email);
}
