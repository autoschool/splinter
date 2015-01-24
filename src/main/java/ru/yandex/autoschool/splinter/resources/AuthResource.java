package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.di.SimpleContainer;
import ru.yandex.autoschool.splinter.models.User;
import ru.yandex.autoschool.splinter.view.freemarker.ViewData;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @SuppressWarnings("unused")
    private Logger logger;

    @GET
    @Path("/register")
    @Template(name = "/auth/register")
    public ViewData showRegisterForm() throws IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("userId") != null) {
            response.sendRedirect("/users/" + (int) session.getAttribute("userId"));
        }

        this.flushError();

        return ViewData;
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ViewData processRegister(@FormParam("login")
                                    @NotNull(message = "login is null")
                                    @Pattern(regexp = "^(?!\\s*$).+", message = "empty string")  String login,
                                    @FormParam("email")
                                    @NotNull(message = "email is null")
                                    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}", message = "not valid email") String email,
                                    @FormParam("name")
                                    @NotNull(message = "name is null")
                                    @Pattern(regexp = "^(?!\\s*$).+", message = "empty string") String name,
                                    @FormParam("sirname")
                                    @NotNull(message = "sirname is null")
                                    @Pattern(regexp = "^(?!\\s*$).+", message = "empty string")
                                    String sirname,
                                    @FormParam("password") String password) throws IOException {
        HttpSession session = request.getSession(true);
        User user = User.findFirst("login = ? OR email = ?", login, email);
        if (user != null) {
            SimpleContainer.getLogger().error("User already exist.", user.getLogin());
            session.setAttribute("error", "User with mentioned login(or email) already exist");
            response.sendRedirect("/register");
            return ViewData;
        }

        user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setSirname(sirname);
        user.setRole("writer");
        user.setPassword(password);
        user.saveIt();

        session.setAttribute("userId", user.getId());

        response.sendRedirect("/users/" + user.getId());
        return ViewData;
    }


    @GET
    @Path("/signin")
    @Template(name = "/auth/login")
    public ViewData showLoginForm() throws IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("userId") != null) {
            response.sendRedirect("/users/" + (int) session.getAttribute("userId"));
            return ViewData;
        }

        this.flushError();
        
        return ViewData;
    }


    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ViewData loginAction(@FormParam("email")
                                @NotNull(message = "email is null")
                                @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}", message = "not valid email")
                                    String name,
                                @FormParam("pass")
                                @NotNull(message = "pass is null")
                                @Pattern(regexp = "^(?!\\s*$).+", message = "empty string") String hash) throws IOException {

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
