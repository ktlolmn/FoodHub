package ptithcm.web.Controller;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ptithcm.web.Entity.NguoiDung;
import ptithcm.web.Entity.ThongTinKhachHang;
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
    	session.removeAttribute("role");
        modelMap.addAttribute("nguoiDung", new NguoiDung());
        return "login/login";
    }
    
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("nguoiDung") NguoiDung nguoiDung,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "login/login";
        }

        NguoiDung dbNguoiDung = nguoiDungService.findByTenDangNhap(nguoiDung.getTenDangNhap());
        if (dbNguoiDung != null && BCrypt.checkpw(nguoiDung.getMatKhau(), dbNguoiDung.getMatKhau())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", nguoiDung.getTenDangNhap());
            if ("Admin".equals(dbNguoiDung.getVaiTro().getTenVaiTro())) {
                session.setAttribute("role", "Admin");
                return "redirect:/admin/monan";
            } else {
                session.setAttribute("role", "User");
                return "redirect:/user/main";
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
                String hashedPassword = BCrypt.hashpw(nguoiDung.getMatKhau(), BCrypt.gensalt());
                nguoiDung.setMatKhau(hashedPassword);
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
    	HttpSession session = request.getSession();
        String tenDangNhap = (String) session.getAttribute("username");
        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
        if(nguoiDung != null) {
        	return"login/change-password";
        }else {
        	return"redirect:/login";
        }
    }
    
    @PostMapping("/change-password")
    public String changePassword(ModelMap modelMap,
        @RequestParam("matKhauCu") String matKhauCu,
        @RequestParam("matKhauMoi") String matKhauMoi,
        @RequestParam("xacNhanMatKhauMoi") String xacNhanMatKhauMoi,
        RedirectAttributes redirectAttributes) {
    	
    	HttpSession session = request.getSession();
        String tenDangNhap = (String) session.getAttribute("username");
        
        if(tenDangNhap == null) {
        	return "redirect:/login";
        }

        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);

    	if(BCrypt.checkpw(matKhauCu,nguoiDung.getMatKhau())){
            if(matKhauCu.equals(matKhauMoi)) {
            	redirectAttributes.addFlashAttribute("errorMessage","Mật khẩu mới trùng mật khẩu cũ!");
        		return"redirect:/change-password";
            }else {
            	if(matKhauMoi.equals(xacNhanMatKhauMoi)) {
            		String hashedPassword = BCrypt.hashpw(matKhauMoi, BCrypt.gensalt());
                	nguoiDung.setMatKhau(hashedPassword);
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
    
    @GetMapping("/forgot-password")
    public String forgotPassword(ModelMap modelMap) {
        return "login/forgot-password";
    }
    
    @PostMapping("/forgot-password")
    public String forgotPasswordPost(@RequestParam("tenDangNhap") String tenDangNhap,
                                     @RequestParam("email") String email,
                                     RedirectAttributes redirectAttributes) {
        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);

        if (nguoiDung == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tài khoản không tồn tại!");
            return "redirect:/forgot-password";
        }else {
        	ThongTinKhachHang thongTinKhachHang = nguoiDung.getThongTinKhachHang();
        	if(thongTinKhachHang == null || thongTinKhachHang.getEmail() == null ) {
        		redirectAttributes.addFlashAttribute("errorMessage", "Thông tin không tồn tại!");
                return "redirect:/forgot-password";
        	}else {
        		if(thongTinKhachHang.getEmail().equals(email)) {
        			String newPassword = generateRandomPassword();
    	            sendNewPasswordToEmail(email, newPassword);
    	            String hashedPasswordString = BCrypt.hashpw(newPassword, BCrypt.gensalt());
    	            nguoiDung.setMatKhau(hashedPasswordString);
    	            nguoiDungService.save(nguoiDung);
    	            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới đã được gửi đến email của bạn!");
    	            return "redirect:/login";
        		}else {
        			redirectAttributes.addFlashAttribute("errorMessage", "Email không đúng!");
                    return "redirect:/forgot-password";
        		}
        	}
        }
    }

    private String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder newPassword = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            newPassword.append(characters.charAt(random.nextInt(characters.length())));
        }

        return newPassword.toString();
    }

    private void sendNewPasswordToEmail(String email, String newPassword) {
        HtmlEmail mail = new HtmlEmail();
        mail.setHostName("smtp.gmail.com"); 
        mail.setSmtpPort(587);
        mail.setAuthenticator(new DefaultAuthenticator("thanhanhynh@gmail.com", "asge zopg qbot nicp"));
        mail.setStartTLSRequired(true);
        try {
            mail.setFrom("thanhanhynh@gmail.com", "Lưu Thành"); 
            mail.addTo(email);
            mail.setSubject("Password Reset");
            mail.setHtmlMsg("Your new password: " + newPassword);
            mail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
