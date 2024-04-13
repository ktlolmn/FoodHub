//package ptithcm.web.Security;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//
//@Component
//public class RoleInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("role")) {
//                    String role = cookie.getValue(); // Lấy giá trị vai trò từ cookie
//                    if (role.equals("ADMIN") && request.getRequestURI().startsWith("/admin")) {
//                        return true; // Cho phép truy cập vào trang admin nếu vai trò là ADMIN
//                    } else if (role.equals("USER") && request.getRequestURI().startsWith("/user")) {
//                        return true; // Cho phép truy cập vào trang user nếu vai trò là USER
//                    }
//                }
//            }
//        }
//        response.sendRedirect("/error"); // Chuyển hướng đến trang lỗi nếu không tìm thấy cookie hoặc vai trò không hợp lệ
//        return false;
//    }
//}
