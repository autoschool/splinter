package ru.yandex.autoschool.splinter.service;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.security.Principal;
import java.util.Map;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(final ContainerRequestContext context) {
        context.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public String getName() {
                        return "John doe";
                    }
                };
            }

            @Override
            public boolean isUserInRole(String s) {
                Map<String, Cookie> cookies = context.getCookies();
                return true;
            }

            @Override
            public boolean isSecure() {
                return context.getSecurityContext().isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return context.getSecurityContext().getAuthenticationScheme();
            }
        });
    }
}
