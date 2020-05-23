package km.exam9.forum.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0,
                                        HttpServletResponse arg1,
                                        Authentication authentication) {
        try {
            redirectStrategy.sendRedirect(arg0, arg1, "/profile");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
