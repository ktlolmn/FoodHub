package ptithcm.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ptithcm.web.Entity.NguoiDung;
import ptithcm.web.Entity.VaiTro;
import ptithcm.web.Service.NguoiDungService;
import ptithcm.web.Service.VaiTroService;

@Controller
public class LoginController {
	
	@Autowired
	NguoiDungService nguoiDungService;
	
	@Autowired 
	VaiTroService vaiTroService;
    
    @GetMapping("/login")
    public String loginForm(ModelMap modelMap) {
    	HttpSession session = request.getSession();
    	session.removeAttribute("username");
        return "login/login";
    }
    
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public String login(@RequestParam("tenDangNhap") String tenDangNhap,
                        @RequestParam("matKhau") String matKhau,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {
        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
        if (nguoiDung != null && nguoiDung.getMatKhau().equals(matKhau)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", tenDangNhap);
            if (nguoiDung.getVaiTro().getTenVaiTro().equals("Admin")) {
                session.setAttribute("role", "Admin");
                return "redirect:/admin/monan";
            } else {
                session.setAttribute("role", "User");
                return "redirect:/user";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/register")
    public String registerForm(ModelMap modelMap) {
    	return"login/register";
    }
    @PostMapping("/register")
    public String register(ModelMap modelMap, NguoiDung nguoiDung,
    		@RequestParam("xacNhanMatKhau") String xacNhanMatKhau, 
    		RedirectAttributes redirectAttributes) {

        NguoiDung nguoiDung2 = nguoiDungService.findByTenDangNhap(nguoiDung.getTenDangNhap());

        if(nguoiDung.getMatKhau().equals(xacNhanMatKhau)) {
        	if (nguoiDung2 != null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Tên đăng nhập đã tồn tại!");
                return "redirect:/register";
            } else {
                VaiTro vaiTroUser = vaiTroService.findByTenVaiTro("User");
                nguoiDung.setVaiTro(vaiTroUser);
                nguoiDungService.save(nguoiDung);
                modelMap.addAttribute("nguoiDung", nguoiDung);

                return "redirect:/login";
            }
        }else {
        	redirectAttributes.addFlashAttribute("errorMessage", "Xác nhận mật khẩu không đúng!");
            return "redirect:/register";
        }
    }
    
    @GetMapping("/change-password")
    public String changePasswordForm() {
    	return"login/change-password";
    }
    
    @PostMapping("/change-password")
    public String changePassword(ModelMap modelMap,
        @RequestParam("matKhauCu") String matKhauCu,
        @RequestParam("matKhauMoi") String matKhauMoi,
        @RequestParam("xacNhanMatKhauMoi") String xacNhanMatKhauMoi,
        RedirectAttributes redirectAttributes) {
    	
    	HttpSession session = request.getSession();
        String tenDangNhap = (String) session.getAttribute("username");

        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
        System.out.print(matKhauCu);
        System.out.print(nguoiDung.getMatKhau().equals(matKhauCu));

    	if(nguoiDung.getMatKhau().equals(matKhauCu)){
            if(nguoiDung.getMatKhau().equals(matKhauMoi)) {
            	redirectAttributes.addFlashAttribute("errorMessage","Mật khẩu mới trùng mật khẩu cũ!");
        		return"redirect:/change-password";
            }else {
            	if(matKhauMoi.equals(xacNhanMatKhauMoi)) {
                	nguoiDung.setMatKhau(matKhauMoi);
                    nguoiDungService.save(nguoiDung);
                    redirectAttributes.addFlashAttribute("errorMessage","Đổi mật khẩu thành công!");
            		return"redirect:/change-password";
                }else {
                	redirectAttributes.addFlashAttribute("errorMessage","Xác nhận mật khẩu không đúng!");
            		return"redirect:/change-password";
                }
            }
    	}else {
    		redirectAttributes.addFlashAttribute("errorMessage","Mật khẩu cũ không đúng!");
    		return"redirect:/change-password";
    	}  
    }

}
