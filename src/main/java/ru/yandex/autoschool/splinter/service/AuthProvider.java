package ru.yandex.autoschool.splinter.service;

import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.context.AuthContext;
import ru.yandex.autoschool.splinter.models.User;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;

/**
 * @author pacahon
 */
@Priority(Priorities.AUTHENTICATION)
public class AuthProvider implements ContainerRequestFilter {

    @Context
    HttpServletRequest request;
    
    @Inject
    Logger logger;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        HttpSession session = request.getSession(true);
        Object userId = session.getAttribute("userId");
        User user = null;
        if (userId != null) {
            logger.debug("Fetched current user ID from session ({})", userId);
            user = User.findById(userId);
            if (user != null) {
                logger.info("Recognized user `{}` using session", user.getLogin());
            } else {
                logger.warn("Couldn't find user using ID from session");
            }
        }

        requestContext.setSecurityContext(new AuthContext(user));
    }
}
