package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.Template;
import ru.yandex.autoschool.splinter.models.User;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.yandex.autoschool.splinter.view.freemarker.ViewData;


/**
 * Created by pacahon on 29.11.14.
 */

@Path("users")
@Produces(MediaType.TEXT_HTML)
public class ProfileResource extends BaseResource {

    @Context
    HttpServletResponse response;

    @GET
    @Template(name = "/user/list")
    public ViewData showProfilesListAction() {
        this.ViewData.set("authUser", securityContext.getUserPrincipal());
        this.ViewData.set("users", User.findAll().limit(3).orderBy("created_at desc"));
        return this.ViewData;
    }

    @GET
    @Path("/{id}")
    @Template(name = "/user/profile")
    public Response showUserProfileAction(@PathParam("id") int id) {
        User user = User.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ViewData.set("profile", user);
        return Response.ok(ViewData).build();
    }
}
