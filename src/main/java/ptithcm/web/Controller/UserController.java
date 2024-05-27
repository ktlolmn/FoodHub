package ptithcm.web.Controller;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.web.Entity.MonAn;
import ptithcm.web.Service.MonAnService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MonAnService monAnService;
	
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
	
	@GetMapping("/xemdon")
	public String view() {
		
		return "user/xemdon";
	}
}
