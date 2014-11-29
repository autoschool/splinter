package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import ru.yandex.autoschool.splinter.models.User;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by pacahon on 29.11.14.
 */

@Path("users")
@Produces(MediaType.TEXT_HTML)
public class ProfileResource extends BaseResource {

    @Context
    HttpServletResponse response;

    @GET
    @Path("/")
    @Template(name = "/templates/user/list.ftl")
    public Map showProfileAction() {
        this.viewData.put("authUser", securityContext.getUserPrincipal());
        this.viewData.put("users", User.findAll().limit(3).orderBy("created_at desc"));
        return this.viewData;
    }

    @GET
    @Path("/{id}")
    @Template(name = "/templates/user/profile.ftl")
    public Map showUserAction(@PathParam("id") int id) {
        this.viewData.put("authUser", securityContext.getUserPrincipal());
        /*FIXME Проверка на сущ-е юзера. Если возвращаем не User, то 404й не увидим*/
        User user = User.findById(id);
        this.viewData.put("profile", user);
        return this.viewData;
    }

}