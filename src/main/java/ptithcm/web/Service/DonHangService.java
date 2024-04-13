package ptithcm.web.Service;

import ptithcm.web.Entity.DonHang;
import java.util.List;

public interface DonHangService {

    List<DonHang> getAllDonHang();

    DonHang getDonHangById(Long id);

    DonHang saveDonHang(DonHang donHang);

    void deleteDonHang(Long id);
}
