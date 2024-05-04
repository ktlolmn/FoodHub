package ptithcm.web.Service;

import java.util.List;
import ptithcm.web.Entity.ChiTietDonHang;

public interface ChiTietDonHangService {
    List<ChiTietDonHang> getChiTietDonHangByDonHangId(Long donHangId);
    void deleteByDonHangId(Long donHangLongId);
}
