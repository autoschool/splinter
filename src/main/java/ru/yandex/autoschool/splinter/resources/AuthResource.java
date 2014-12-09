package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import ru.yandex.autoschool.splinter.models.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Map;

import static ru.yandex.autoschool.splinter.SplinterApplication.LOGGER;

/**
 * Created by pacahon on 28.11.14.
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
@ErrorTemplate(name = "/templates/error.ftl")
public class AuthResource extends BaseResource {

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;

    @GET
    @Path("/signin")
    @Template(name = "/templates/auth/login.ftl")
    public Map showLoginForm() throws IOException {
        this.viewData.put("authUser", securityContext.getUserPrincipal());
        HttpSession session = request.getSession(true);
        if (session.getAttribute("userId") != null) {
            response.sendRedirect("/users/" + (int) session.getAttribute("userId"));
            return this.viewData;
        }
        String error = (String) session.getAttribute("error");
        if (error != null) {
            this.viewData.put("error", error);
            session.removeAttribute("error");
        }
        return this.viewData;
    }


    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String loginAction(@FormParam("email") String name,
                               @FormParam("pass") String hash) throws IOException {

        HttpSession session = request.getSession(true);

        if (session.getAttribute("userId") != null) {
            response.sendRedirect("/users/" + session.getAttribute("userId"));
            return "";
        }

        User user = User.findByUnknownIdentifierAndPassword(name, hash);

        if (user == null) {
            LOGGER.info("Received incorrect email-password pair, halting authorization");
            session.setAttribute("error", "Incorrect login-password pair");
            response.sendRedirect("/signin");
            return "";
        }

        session.setAttribute("userId", user.getId());
        LOGGER.debug("Saving user ID in session after successful authorization ({}).", user.getId());

        response.sendRedirect("/users/" + user.getId());

        return "";
    }

    @GET
    @Path("/signout")
    public String logoutAction() throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        response.sendRedirect("/");

        return "";
    }
}