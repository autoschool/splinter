package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import ru.yandex.autoschool.splinter.models.Comment;
import ru.yandex.autoschool.splinter.models.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@Path("/posts/")
@Produces(MediaType.TEXT_HTML)
@ErrorTemplate(name = "/templates/error.ftl")
public class PostResource {

    @GET
    @Path("/")
    @Template(name = "/templates/post/list.ftl")
    public List<Post> listAction() {
        return Post.findAll();
    }

    @GET
    @Path("/{id}")
    @Template(name = "/templates/post/single.ftl")
    public Post singleAction(@PathParam("id") int id) {
        return Post.findById(id);
    }

    @GET
    @Path("/new")
    @Template(name = "/templates/post/form.ftl")
    public Post formAction() {
        return new Post();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Template(name = "/templates/post/single.ftl")
    public Response saveAction(@FormParam("title") String title, @FormParam("content") String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.saveIt();
        URI targetURIForRedirection = URI.create("/posts/" + post.getId());
        return Response.seeOther(targetURIForRedirection).build();
    }

    @POST
    @Path("/{id}/comment")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Template(name = "/templates/post/single.ftl")
    public Post commentAction(@PathParam("id") int id, @FormParam("content") String content) {
        Post post = Post.findById(id);
        Comment comment = new Comment();
        comment.setContent(content);
        post.add(comment);
        post.saveIt();
        return post;
    }
}
