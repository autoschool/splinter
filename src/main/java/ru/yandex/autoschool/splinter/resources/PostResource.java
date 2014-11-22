package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Paginator;
import ru.yandex.autoschool.splinter.models.Comment;
import ru.yandex.autoschool.splinter.models.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map listAction(@DefaultValue("1") @QueryParam("page") int page) {
        Paginator p = new Paginator(Post.class, 3, "*").orderBy("created_at desc");
        int pageCount = (int)p.pageCount();
        if (pageCount == 0 || page < 1) {
            page = 1;
        } else if (page > pageCount) {
            page = pageCount;
        }
        LazyList posts = p.getPage(page);

        String linkUrl = "/posts/";

        Map root = new HashMap();
        root.put("model", posts);

        Map<String, Object> pagination = new HashMap();
        root.put("pagination", pagination);
        pagination.put("currentPage", page);
        pagination.put("totalPages", pageCount);
        pagination.put("linkUrl", linkUrl);

        return root;
    }

    @GET
    @Path("/{id}")
    @Template(name = "/templates/post/single.ftl")
    public Response singleAction(@PathParam("id") int id) {
        Post post = Post.findById(id);
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(post).build();
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
