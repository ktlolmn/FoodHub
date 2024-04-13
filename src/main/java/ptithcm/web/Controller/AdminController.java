package ptithcm.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ptithcm.web.Entity.MonAn;
import ptithcm.web.Service.MonAnService;
import java.io.IOException;
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
        for (MonAn monAn : danhSachMonAn) {
            if (monAn.getImg() != null) {
                String base64Image = Base64.getEncoder().encodeToString(monAn.getImg());
                monAn.setBase64Image(base64Image);
            }
        }
        model.addAttribute("danhSachMonAn", danhSachMonAn);
        model.addAttribute("monAn", new MonAn());
        return "monan";
    }
    
    @PostMapping("/monan/save")
    public String saveMonAn(@ModelAttribute("monAn") MonAn monAn,
                            @RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) throws IOException {
        if (!file.isEmpty()) {
            monAn.setImg(file.getBytes());
        }
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
    public String updateMonAn(@ModelAttribute("monAn") MonAn monAn,
                              @RequestParam("file") MultipartFile file,
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
        monAnService.deleteMonAn(id);
        redirectAttributes.addFlashAttribute("successMessage", "Món ăn đã được xoá thành công.");
        return "redirect:/admin/monan";
    }
}
