package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.models.User;
import ru.yandex.autoschool.splinter.view.freemarker.ViewData;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;

/**
 * Created by pacahon on 28.11.14.
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
@ErrorTemplate(name = "/error")
public class AuthResource extends BaseResource {

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    @Context
    SecurityContext securityContext;
    
    @Inject
    private Logger logger;

    @GET
    @Path("/signin")
    @Template(name = "/auth/login")
    public ViewData showLoginForm() throws IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("userId") != null) {
            response.sendRedirect("/users/" + (int) session.getAttribute("userId"));
            return ViewData;
        }
        String error = (String) session.getAttribute("error");
        if (error != null) {
            ViewData.set("error", error);
            session.removeAttribute("error");
        }
        return ViewData;
    }


    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ViewData loginAction(@FormParam("email") String name,
                               @FormParam("pass") String hash) throws IOException {

        HttpSession session = request.getSession(true);
        
        if (session.getAttribute("userId") != null) {
            response.sendRedirect("/users/" + session.getAttribute("userId"));
            return ViewData;
        }

        User user = User.findByUnknownIdentifierAndPassword(name, hash);

        if (user == null) {
            logger.info("Received incorrect email-password pair, halting authorization");
            session.setAttribute("error", "Incorrect login-password pair");
            response.sendRedirect("/signin");
            return ViewData;
        }

        session.setAttribute("userId", user.getId());
        logger.debug("Saving user ID in session after successful authorization ({}).", user.getId());

        response.sendRedirect("/users/" + user.getId());

        return ViewData;
    }

    @GET
    @Path("/signout")
    public ViewData logoutAction() throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        response.sendRedirect("/");

        return ViewData;
    }
}
