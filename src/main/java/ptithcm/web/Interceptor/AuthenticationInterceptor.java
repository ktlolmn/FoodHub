package ptithcm.web.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(AuthenticationInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String uri = request.getRequestURI();
        logger.info("Requested URI: " + uri);

        if (uri.startsWith("/admin/") && !isRole(request, "Admin")) {
            logger.info("Non-admin user attempting to access /admin/*");
            response.sendRedirect("/login");
            return false;
        }

        if (uri.startsWith("/user/") && !isRole(request, "User")) {
            logger.info("Non-user role attempting to access /user/*");
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

    private boolean isRole(HttpServletRequest request, String role) {
        String userRole = (String) request.getSession().getAttribute("role");
        logger.info("User role: " + userRole);
        return userRole != null && userRole.equals(role);
    }
}
