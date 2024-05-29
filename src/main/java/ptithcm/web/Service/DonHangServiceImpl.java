package ptithcm.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.web.Entity.ChiTietDonHang;
import ptithcm.web.Entity.DonHang;
import ptithcm.web.Entity.MonAn;
import ptithcm.web.Entity.NguoiDung;
import ptithcm.web.Repository.ChiTietDonHangRepository;
import ptithcm.web.Repository.DonHangRepository;
import ptithcm.web.Repository.MonAnRepository;
import ptithcm.web.Repository.NguoiDungRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;
    
    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    
    @Autowired
    private MonAnRepository monAnRepository;

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

    @Override
    public List<DonHang> getDonHangByTrangThai(String trangThai){
        return donHangRepository.findByTrangThai(trangThai);
    }
    
    @Override
    public void saveOrder(List<Long> foodIds, String address, Long userId, Double total) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        DonHang donHang = new DonHang();
        donHang.setNguoiDung(nguoiDung);
        donHang.setDiaChi(address);
        donHang.setTrangThai("Chưa xác nhận");
        donHang.setNgayTao(LocalDateTime.now());
        donHangRepository.save(donHang);

        for (Long foodId : foodIds) {
            MonAn monAn = monAnRepository.findById(foodId).orElseThrow(() -> new RuntimeException("Food not found"));
            ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
            chiTietDonHang.setDonHang(donHang);
            chiTietDonHang.setMonAn(monAn);
            chiTietDonHang.setSoLuong(1); // Giả sử số lượng là 1, có thể thay đổi nếu cần
            chiTietDonHangRepository.save(chiTietDonHang);
        }
    }

	@Override
	public List<DonHang> findAllByTenDangNhap(String tenDangNhap) {
		return donHangRepository.findAllByNguoiDungTenDangNhap(tenDangNhap);
	}
	
	@Override
	public List<DonHang> findAllByNguoiDungId(Long nguoiDungId) {
        return donHangRepository.findAllByNguoiDungId(nguoiDungId);
    }
}
