package ru.yandex.autoschool.splinter.service;

import ru.yandex.autoschool.splinter.context.AuthContext;
import ru.yandex.autoschool.splinter.models.User;

import javax.annotation.Priority;
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

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        HttpSession session = request.getSession(true);
        Object userId = session.getAttribute("userId");

        User user = null;
        if (userId != null) {
            user = User.findById(userId);
            System.out.println("get user_id from session = " + user.getId());
        }

        requestContext.setSecurityContext(new AuthContext(user));
    }
}
