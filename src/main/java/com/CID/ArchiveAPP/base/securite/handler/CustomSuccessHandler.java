package com.CID.ArchiveAPP.base.securite.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String ctx = request.getContextPath();  // => "/archive-app"
        String target = ctx + "/";

        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
        if (has(auths, "ADMIN"))  target = ctx + "/admin-dashboard";
        else if (has(auths, "AGENT")) target = ctx + "/dashbord_agent_archivage";
        else if (has(auths, "CADRE")) target = ctx + "/dashbord_cadre";
        else if (has(auths, "CHEF"))  target = ctx + "/dashbord_chef";

        getRedirectStrategy().sendRedirect(request, response, target);
    }

    private boolean has(Collection<? extends GrantedAuthority> auths, String role) {
        return auths.stream().anyMatch(a ->
                a.getAuthority().equals(role) || a.getAuthority().equals("ROLE_" + role)
        );
    }
}
