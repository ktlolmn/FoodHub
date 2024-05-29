package ptithcm.web.Controller;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import ptithcm.web.Entity.ChiTietDonHang;
import ptithcm.web.Entity.DonHang;
import ptithcm.web.Entity.MonAn;
import ptithcm.web.Entity.NguoiDung;
import ptithcm.web.Entity.ThongTinKhachHang;
import ptithcm.web.Service.ChiTietDonHangService;
import ptithcm.web.Service.DonHangService;
import ptithcm.web.Service.MonAnService;
import ptithcm.web.Service.NguoiDungService;
import ptithcm.web.Service.ThongTinKhachHangService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MonAnService monAnService;
	
	@Autowired
	private DonHangService donHangService;
	
	@Autowired
	private ChiTietDonHangService chiTietDonHangService;

	@Autowired
	private NguoiDungService nguoiDungService;

	@Autowired
	private ThongTinKhachHangService thongTinKhachHangService;
	
	@GetMapping("/main")
	public String viewMonAn(Model model) {
		List<MonAn> danhSachMonAn = monAnService.getAllMonAn();
		Collections.sort(danhSachMonAn, (monAn1, monAn2) -> Boolean.compare(monAn2.getTrangThai(), monAn1.getTrangThai()));
		for (MonAn monAn : danhSachMonAn) {
				if (monAn.getImg() != null) {
					String base64Image = Base64.getEncoder().encodeToString(monAn.getImg());
					monAn.setBase64Image(base64Image);
			}
		}
		model.addAttribute("danhSachMonAn", danhSachMonAn);
		model.addAttribute("monAn", new MonAn());
		return "user/main";
	}
	///////////////////
	@PostMapping("/save-order")
    public String saveOrder(@SessionAttribute(name = "username") String tenDangNhap,
    		@RequestParam("foodIds") String foodIds,
    		@RequestParam("total") String total, Model model, RedirectAttributes redirectAttributes) {
		NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
		ThongTinKhachHang thongTinKhachHang = thongTinKhachHangService.getThongTinKhachHangByNguoiDungId(nguoiDung.getId());
		if (thongTinKhachHang != null) {
			model.addAttribute("foodIds", foodIds);
	        model.addAttribute("total", total);
	        return "user/save-order";
		}else {
			redirectAttributes.addFlashAttribute("message", "Vui lòng nhập thông tin cá nhân");
	        return "redirect:/user/main?error?";
		}
    }

	@PostMapping("/confirm-order")
    public String confirmOrder(@SessionAttribute(name = "username") String tenDangNhap,HttpServletRequest request,
    		@RequestParam("foodIds") String foodIds, @RequestParam("total") Double total, @RequestParam("address") String address) {
        try {
        	// Lấy ID người dùng từ session
			NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
        	Long userId = nguoiDung.getId();
        	
        	// Log the userId
            System.out.println("User ID: " + userId);
            if (userId == null) {
                throw new IllegalArgumentException("User ID must not be null");
            }

            // Chuyển đổi foodIds từ chuỗi JSON sang danh sách các ID món ăn
            List<Long> foodIdList = new ObjectMapper().readValue(foodIds, new TypeReference<List<Long>>() {});
            // Log the foodIdList
            System.out.println("Food IDs: " + foodIdList);
            
            // Lưu đơn hàng và chi tiết đơn hàng
            donHangService.saveOrder(foodIdList, address, userId, total);

        } catch (Exception e) {
        	System.out.println(e);
            e.printStackTrace();
            return "redirect:/user/main?error";
        }
        return "redirect:/user/xemdon";
    }
	////////////////////
	@GetMapping("/xemdon")
	public String viewMonAn(Model model, @SessionAttribute(name = "username") String tenDangNhap) {
		// Lấy thông tin người dùng từ tên đăng nhập
	    NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
	    
	    // Lấy danh sách đơn hàng theo ID người dùng
	    List<DonHang> danhSachDonHang = donHangService.findAllByNguoiDungId(nguoiDung.getId());
	    
	    // Thêm danh sách đơn hàng vào model
	    model.addAttribute("danhSachDonHang", danhSachDonHang);
	    
	    // Trả về tên view để hiển thị danh sách đơn hàng
	    return "user/xemdon";
	}
	
	@GetMapping("/xemdon/{id}")
    public String viewChiTietDonHang(@PathVariable("id") Long id,
    		@SessionAttribute(name = "username") String tenDangNhap, Model model) {
		NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
        DonHang donHang = donHangService.getDonHangById(id);
        List<ChiTietDonHang> chiTietDonHang = chiTietDonHangService.getChiTietDonHangByDonHangId(donHang.getId());
        List<DonHang> danhSachDonHang = donHangService.findAllByNguoiDungId(nguoiDung.getId());
        Long tong = 0L;
		for (ChiTietDonHang ct : chiTietDonHang) {
			tong += ct.getMonAn().getGia();
		}

        model.addAttribute("donHang", donHang);
		model.addAttribute("tong", tong);
        model.addAttribute("chiTietDonHang", chiTietDonHang);
        model.addAttribute("danhSachDonHang", danhSachDonHang);
        return "user/xemdon";
    }

	@GetMapping("/infor")
	public String getMethodName(@SessionAttribute(name = "username") String tenDangNhap, Model model) {
		NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
        ThongTinKhachHang thongTinKhachHang = thongTinKhachHangService.getThongTinKhachHangByNguoiDungId(nguoiDung.getId());
		model.addAttribute("thongTinKhachHang", thongTinKhachHang);
		return "user/infor";
	}
	@PostMapping("/infor/save")
public String saveThongTinCaNhan(@SessionAttribute(name = "username") String tenDangNhap,
                                  @ModelAttribute("thongTinKhachHang") ThongTinKhachHang thongTinKhachHang,
                                  BindingResult bindingResult,
                                  Model model) {
    // Kiểm tra nếu có lỗi trong việc binding dữ liệu
	System.out.println(thongTinKhachHang.getHoTen());
	System.out.println(thongTinKhachHang.getEmail());
	System.out.println(thongTinKhachHang.getSoDienThoai());
	System.out.println(thongTinKhachHang.getNguoiDung());
	System.out.println(thongTinKhachHang.getId());


    
    // Thực hiện lưu thông tin cá nhân vào cơ sở dữ liệu
    try {
        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
        if (thongTinKhachHang.getNguoiDung() == null) {
			thongTinKhachHang.setNguoiDung(nguoiDung);
		}
		if (thongTinKhachHangService.isEmailDuplicated(thongTinKhachHang.getEmail(), thongTinKhachHang.getNguoiDung().getId())) {
			model.addAttribute("error", "Đã xảy ra lỗi khi lưu thông tin cá nhân.");
		}
        thongTinKhachHangService.saveThongTinKhachHang(thongTinKhachHang);
        model.addAttribute("success", "Lưu thông tin cá nhân thành công.");
    } catch (Exception e) {
        model.addAttribute("error", "Đã xảy ra lỗi khi lưu thông tin cá nhân.");
        e.printStackTrace(); // Log lỗi ra console
    }
    
    return "redirect:/user/infor";
}



	
}
