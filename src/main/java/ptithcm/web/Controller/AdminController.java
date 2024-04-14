package ptithcm.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.web.Entity.ChiTietDonHang;
import ptithcm.web.Entity.DonHang;
import ptithcm.web.Entity.MonAn;
import ptithcm.web.Service.ChiTietDonHangService;
import ptithcm.web.Service.DonHangService;
import ptithcm.web.Service.MonAnService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MonAnService monAnService;

	@GetMapping("/monan")
	public String viewMonAn(Model model) {
		List<MonAn> danhSachMonAn = monAnService.getAllMonAn();
		List<MonAn> list = new ArrayList<MonAn>();
		for (MonAn monAn : danhSachMonAn) {
			if(monAn.getTrangThai()) {
				if (monAn.getImg() != null) {
					String base64Image = Base64.getEncoder().encodeToString(monAn.getImg());
					monAn.setBase64Image(base64Image);
				}
				list.add(monAn);
			}
		}
		model.addAttribute("danhSachMonAn", list);
		model.addAttribute("monAn", new MonAn());
		return "admin/monan";
	}

	@PostMapping("/monan/save")
	public String saveMonAn(@ModelAttribute("monAn") MonAn monAn, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws IOException {
		if (!file.isEmpty()) {
			monAn.setImg(file.getBytes());
		}
		monAn.setTrangThai(true);
		monAnService.saveMonAn(monAn);
		redirectAttributes.addFlashAttribute("successMessage", "Món ăn đã được lưu thành công.");
		return "redirect:/admin/monan";
	}

	@GetMapping("/monan/edit/{id}")
	public String editMonAn(@PathVariable("id") Long id, Model model) {
		MonAn monAn = monAnService.getMonAnById(id);
		String base64Image = Base64.getEncoder().encodeToString(monAn.getImg());
		monAn.setBase64Image(base64Image);
		model.addAttribute("monAn", monAn);
		return "admin/monan";
	}

	@PostMapping("/monan/update")
	public String updateMonAn(@ModelAttribute("monAn") MonAn monAn, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws IOException {
		if (!file.isEmpty()) {
			monAn.setImg(file.getBytes());
		}
		monAnService.saveMonAn(monAn);
		redirectAttributes.addFlashAttribute("successMessage", "Món ăn đã được cập nhật thành công.");
		return "redirect:/admin/monan";
	}

	@GetMapping("/monan/delete/{id}")
	public String deleteMonAn(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		MonAn monAn = monAnService.getMonAnById(id);
		monAn.setTrangThai(false);
		monAnService.saveMonAn(monAn);
		redirectAttributes.addFlashAttribute("successMessage", "Món ăn đã được xoá thành công.");
		return "redirect:/admin/monan";
	}

	@Autowired
    private DonHangService donHangService;
	@Autowired
	private ChiTietDonHangService chiTietDonHangService;

    @GetMapping("/donhang")
    public String viewDonHang(Model model) {
        List<DonHang> danhSachDonHang = donHangService.getAllDonHang();
        model.addAttribute("danhSachDonHang", danhSachDonHang);
        return "admin/donhang";
    }

    @GetMapping("/donhang/{id}")
    public String viewChiTietDonHang(@PathVariable("id") Long id, Model model) {
        DonHang donHang = donHangService.getDonHangById(id);
        List<ChiTietDonHang> chiTietDonHang = chiTietDonHangService.getChiTietDonHangByDonHangId(donHang.getId());
        model.addAttribute("donHang", donHang);
        model.addAttribute("chiTietDonHang", chiTietDonHang);
        return "admin/chitietdonhang";
    }

    @PostMapping("/donhang/confirm/{id}")
    public String confirmDonHang(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        DonHang donHang = donHangService.getDonHangById(id);
        donHang.setTrangThai("Đã xác nhận");
        donHangService.saveDonHang(donHang);
        redirectAttributes.addFlashAttribute("successMessage", "Đơn hàng đã được xác nhận.");
        return "redirect:/admin/donhang";
    }

    @PostMapping("/donhang/reject/{id}")
    public String rejectDonHang(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        DonHang donHang = donHangService.getDonHangById(id);
        donHang.setTrangThai("Đã từ chối");
        donHangService.saveDonHang(donHang);
        redirectAttributes.addFlashAttribute("successMessage", "Đơn hàng đã được từ chối.");
        return "redirect:/admin/donhang";
    }

}
