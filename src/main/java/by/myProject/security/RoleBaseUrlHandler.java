package by.myProject.security;

import by.myProject.model.domain.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class RoleBaseUrlHandler extends SimpleUrlAuthenticationSuccessHandler {

    //provide redirection logic
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {

        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    /**
     * Invokes the configured RedirectStrategy with the URL returned by the
     * determineTargetUrl method.
     */
    @Override
    protected void handle(HttpServletRequest request,
                          HttpServletResponse response,
                          Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }


    /**
     * Builds the target URL according to the logic defined
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    protected String determineTargetUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        if (isStudent(roles)) {
            url = "/studentPage";
        } else if (isAdmin(roles)) {
            url = "/adminPage";
        } else if (isTeacher(roles)) {
            url = "/teacherPage";
        } else {
            url = "/403";
        }
        return url;
    }

    private boolean isStudent(List<String> roles) {
        if (roles.contains("ROLE_STUDENT")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isTeacher(List<String> roles) {
        if (roles.contains("ROLE_TEACHER")) {
            return true;
        }
        return false;
    }

}