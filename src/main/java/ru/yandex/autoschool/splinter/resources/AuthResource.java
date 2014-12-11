package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import ru.yandex.autoschool.splinter.models.User;
import ru.yandex.autoschool.splinter.view.ViewData;


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
@ErrorTemplate(name = "/templates/error.ftl")
public class AuthResource extends BaseResource {

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    @Context
    SecurityContext securityContext;

    @GET
    @Path("/signin")
    @Template(name = "/templates/auth/login.ftl")
    public ViewData showLoginForm() {
        return ViewData;
    }


    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ViewData loginAction(@FormParam("email") String name,
                               @FormParam("pass") String hash) throws IOException {

        User user = User.findFirst("email = ? and password = ?", name, hash);

        if (user == null) {
            System.out.println("name or password is wrong");
            return ViewData;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("userId", user.getId());
        System.out.println("set user_id in session" + user.getId());

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