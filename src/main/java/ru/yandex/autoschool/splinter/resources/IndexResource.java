package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import ru.yandex.autoschool.splinter.config.ApplicationConfig;
import ru.yandex.autoschool.splinter.models.Post;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
@ErrorTemplate(name = "/templates/error.ftl")
public class IndexResource {
    @GET
    @Path("/")
    @Template(name = "/templates/index/index.ftl")
    public List<Post> indexAction() {
        return Post.findAll().limit(ApplicationConfig.POSTS_PER_PAGE).orderBy("created_at desc");
    }

    @GET
    @Path("/about")
    @Template(name = "/templates/index/about.ftl")
    public String aboutAction() {
        return "about page";
    }

}
