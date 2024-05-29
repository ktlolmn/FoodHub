package ptithcm.web.Controller;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import ptithcm.web.Entity.DonHang;
import ptithcm.web.Entity.MonAn;
import ptithcm.web.Service.DonHangService;
import ptithcm.web.Service.MonAnService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MonAnService monAnService;
	
	@Autowired
	private DonHangService donHangService;
	
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
    public String saveOrder(@RequestParam("foodIds") String foodIds, @RequestParam("total") String total, Model model) {
        model.addAttribute("foodIds", foodIds);
        model.addAttribute("total", total);
        return "user/save-order";
    }

	@PostMapping("/confirm-order")
    public String confirmOrder(HttpServletRequest request,
    		@RequestParam("foodIds") String foodIds, @RequestParam("total") Double total, @RequestParam("address") String address) {
        try {
        	// Lấy ID người dùng từ session
        	Long userId = (Long) request.getSession().getAttribute("userId");
        	
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
	public String view() {
		
		return "user/xemdon";
	}
}
