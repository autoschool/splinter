package ru.yandex.autoschool.splinter.context;


import ru.yandex.autoschool.splinter.models.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Created by pacahon on 28.11.14.
 */
public class AuthContext implements SecurityContext {

    private User user;

    public AuthContext(final User user) {
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
