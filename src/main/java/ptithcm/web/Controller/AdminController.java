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
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MonAnService monAnService;

	@GetMapping("/monan")
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
		return "admin/monan";
	}
	
	@PostMapping("/monan/filter")
	public String viewMonAnFilter(Model model,
			@RequestParam("keyWord") String ten) {
		List<MonAn> danhSachMonAn = monAnService.getAllMonAn();
		List<MonAn> danhSachMonAnFilter = new ArrayList<MonAn>();
		for(MonAn monAn: danhSachMonAn) {
			if(monAn.getTen().toLowerCase().contains(ten.toLowerCase())) {
				danhSachMonAnFilter.add(monAn);
			}
		}
		Collections.sort(danhSachMonAnFilter, (monAn1, monAn2) -> Boolean.compare(monAn2.getTrangThai(), monAn1.getTrangThai()));
		for (MonAn monAn : danhSachMonAnFilter) {
				if (monAn.getImg() != null) {
					String base64Image = Base64.getEncoder().encodeToString(monAn.getImg());
					monAn.setBase64Image(base64Image);
			}
		}
		model.addAttribute("danhSachMonAn", danhSachMonAnFilter);
		model.addAttribute("monAn", new MonAn());
		return "admin/monan";
	}

	@PostMapping("/monan/save")
	public String saveMonAn(@ModelAttribute("monAn") MonAn monAn, 
			@RequestParam("file") MultipartFile file,
			@RequestParam(value = "keepImage", required = false) String keepImage,
			RedirectAttributes redirectAttributes) throws IOException {
		Boolean keepImgBoolean = "on".equals(keepImage);
		if (!file.isEmpty() && !keepImgBoolean) {
			monAn.setImg(file.getBytes());
		}
		if(keepImgBoolean) {
			MonAn monAnCu = monAnService.getMonAnById(monAn.getId());
			monAn.setImg(monAnCu.getImg());
		}
		try {
			monAn.setTrangThai(true);
			monAnService.saveMonAn(monAn);
			redirectAttributes.addFlashAttribute("message", "Thành công.");
			return "redirect:/admin/monan?success?";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Lưu thất bại!");
			return "redirect:/admin/monan?error?";
		}
	}


	@GetMapping("/monan/tat/{id}")
	public String tatMonAn(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		MonAn monAn = monAnService.getMonAnById(id);
		System.out.print("tẳt");
		monAn.setTrangThai(false);
		monAnService.saveMonAn(monAn);
		return "redirect:/admin/monan";
	}
	
	@GetMapping("/monan/bat/{id}")
	public String batMonAn(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		MonAn monAn = monAnService.getMonAnById(id);
		monAn.setTrangThai(true);
		System.out.print("bật");
		monAnService.saveMonAn(monAn);
		return "redirect:/admin/monan";
	}
	
	@GetMapping("/monan/toggle/{id}")
    public String toggleMonAn(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        MonAn monAn = monAnService.getMonAnById(id);
        monAn.setTrangThai(!monAn.getTrangThai());
        monAnService.saveMonAn(monAn);

        redirectAttributes.addFlashAttribute("message", "Món ăn đã được cập nhật!"); // Optional flash message
        return "redirect:/admin/monan";
    }
	
	@GetMapping("/monan/xoa/{id}")
	public String xoaMonAn(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
	    try{
			monAnService.deleteMonAn(id);
			return "redirect:/admin/monan";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Món ăn đang được đặt, không thể xóa.");
			return "redirect:/admin/monan?error?";
		}
	}

	@Autowired
    private DonHangService donHangService;
	@Autowired
	private ChiTietDonHangService chiTietDonHangService;

    @GetMapping("/donhang")
    public String viewDonHang(Model model) {
        List<DonHang> danhSachDonHang = donHangService.getAllDonHang();
		Set<String> danhSachTrangThai = danhSachDonHang.stream()
            .map(DonHang::getTrangThai)
            .collect(Collectors.toSet());
		
		model.addAttribute("danhSachTrangThai", danhSachTrangThai);
        model.addAttribute("danhSachDonHang", danhSachDonHang);
        return "admin/donhang";
    }

	@PostMapping("/donhang/filter")
    public String filterDonHang(@RequestParam("trangThai") String trangThai, Model model) {
        List<DonHang> danhSachDonHang;

        if (trangThai == null || trangThai.isEmpty()) {
            danhSachDonHang = donHangService.getAllDonHang();
        } else {
            danhSachDonHang = donHangService.getDonHangByTrangThai(trangThai);
        }
        Set<String> danhSachTrangThai = donHangService.getAllDonHang().stream()
                .map(DonHang::getTrangThai)
                .collect(Collectors.toSet());

        model.addAttribute("danhSachDonHang", danhSachDonHang);
        model.addAttribute("danhSachTrangThai", danhSachTrangThai);
        return "admin/donhang";
    }
    

    @GetMapping("/donhang/{id}")
    public String viewChiTietDonHang(@PathVariable("id") Long id, Model model) {
        DonHang donHang = donHangService.getDonHangById(id);
        List<ChiTietDonHang> chiTietDonHang = chiTietDonHangService.getChiTietDonHangByDonHangId(donHang.getId());
        Long tong = 0L;
		for (ChiTietDonHang ct : chiTietDonHang) {
			tong += ct.getMonAn().getGia();
		}

        model.addAttribute("donHang", donHang);
		model.addAttribute("tong", tong);
        model.addAttribute("chiTietDonHang", chiTietDonHang);
        List<DonHang> danhSachDonHang = donHangService.getAllDonHang();
        model.addAttribute("danhSachDonHang", danhSachDonHang);
        return "admin/donhang";
    }

    @PostMapping("/donhang/confirm/{id}")
    public String confirmDonHang(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        DonHang donHang = donHangService.getDonHangById(id);
        try {
			chiTietDonHangService.deleteByDonHangId(id);
			donHang.setTrangThai("Đã xác nhận");
	        donHangService.saveDonHang(donHang);
	        redirectAttributes.addFlashAttribute("successMessage", "Đơn hàng đã được xác nhận.");
	        return "redirect:/admin/donhang";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("successMessage", "Có lỗi xảy ra.");
	        return "redirect:/admin/donhang";
		}
        
    }

    @PostMapping("/donhang/reject/{id}")
    public String rejectDonHang(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        DonHang donHang = donHangService.getDonHangById(id);
        donHang.setTrangThai("Đã từ chối");
        donHangService.saveDonHang(donHang);
        redirectAttributes.addFlashAttribute("successMessage", "Đơn hàng đã được từ chối.");
        return "redirect:/admin/donhang";
    }
    
    @PostMapping("/donhang/search")
	public String viewDonHangFilter(Model model,
			@RequestParam("keyWord") String ten) {
		List<DonHang> danhSachDonHang = donHangService.getAllDonHang();
		List<DonHang> danhSachDonHangFilter = new ArrayList<DonHang>();
		for(DonHang donHang: danhSachDonHang) {
			System.out.print(donHang.getNguoiDung().getThongTinKhachHang().getHoTen());
			System.out.print(donHang.getNguoiDung().getThongTinKhachHang().getHoTen().toLowerCase().contains(ten.toLowerCase()));
			if(donHang.getNguoiDung().getThongTinKhachHang().getHoTen().toLowerCase().contains(ten.toLowerCase())) {
				danhSachDonHangFilter.add(donHang);
			}
		}
		
		model.addAttribute("danhSachDonHang", danhSachDonHangFilter);
		return "admin/donhang";
	}

}
