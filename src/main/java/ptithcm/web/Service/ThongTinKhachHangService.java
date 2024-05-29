package ptithcm.web.Service;

import ptithcm.web.Entity.ThongTinKhachHang;

public interface ThongTinKhachHangService {
    ThongTinKhachHang getThongTinKhachHangByNguoiDungId(Long nguoiDungId);
    boolean isEmailDuplicated(String email, Long nguoiDungId);
    void saveThongTinKhachHang(ThongTinKhachHang thongTinKhachHang);
}
