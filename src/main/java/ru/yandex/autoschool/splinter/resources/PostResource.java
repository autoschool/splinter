package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Paginator;
import ru.yandex.autoschool.splinter.config.ApplicationConfig;
import ru.yandex.autoschool.splinter.models.Comment;
import ru.yandex.autoschool.splinter.models.Post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
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
    private static final String RESOURCE_PATH = "/posts/";
    @GET
    @Path("/")
    @Template(name = "/templates/post/list.ftl")
    public Map listAction(@DefaultValue("1") @QueryParam("page") int page) {
        Paginator p = new Paginator(Post.class, ApplicationConfig.POSTS_PER_PAGE, "*").orderBy("created_at desc");
        int pageCount = Math.max((int) p.pageCount(), 1);
        int pageNumber = (page > pageCount) ? pageCount : Math.max(1, page);
        LazyList posts = p.getPage(pageNumber);

        Map root = new HashMap();
        root.put("model", posts);

        Map<String, Object> pagination = new HashMap();
        root.put("pagination", pagination);
        pagination.put("currentPage", pageNumber);
        pagination.put("totalPages", pageCount);
        pagination.put("linkUrl", RESOURCE_PATH);

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
        
        Map root = new HashMap();
        root.put("model", post);

        return Response.ok(root).build();
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
    public Response saveAction(@FormParam("title")
                               @Pattern(regexp = "^(?!\\s*$).+", message = "empty string") String title
                              ,@FormParam("content")
                               @Pattern(regexp = "^(?!\\s*$).+", message = "empty string") String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.saveIt();
        URI targetURIForRedirection = URI.create(RESOURCE_PATH + post.getId());
        return Response.seeOther(targetURIForRedirection).build();
    }
    @POST
    @Path("/{id}/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Template(name= "/templates/post/single.ftl")
    public Response editPostAction(@PathParam("id") int id,
                                   @FormParam("title")
                                   @Pattern(regexp = "^(?!\\s*$).+", message = "empty string") String title,
                                   @FormParam("content")
                                   @Pattern(regexp = "^(?!\\s*$).+", message = "empty string") String content){
        Post post=Post.findById(id);
        post.setTitle(title);
        post.setContent(content);
        post.saveIt();
        URI targetURIForRedirection = URI.create(RESOURCE_PATH + post.getId());
        return Response.seeOther(targetURIForRedirection).build();
    }
    @POST
    @Path("/{id}/delete")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Template(name= "/templates/post/single.ftl")
    public Response deletePostAction(@PathParam("id") @NotNull(message = "not null") int id){
        Post post=Post.findById(id);
        post.delete();
        URI targetURIForRedirection = URI.create("/");
        return Response.seeOther(targetURIForRedirection).build();
    }

    @POST
    @Path("/{id}/comment")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Template(name = "/templates/post/single.ftl")
    public Response commentAction(@PathParam("id")  @NotNull(message = "not null")  int id
                                  ,@FormParam("content")
                                   @Pattern(regexp = "^(?!\\s*$).+", message = "empty string") String commentContent) {
        Post post = Post.findById(id);
        Comment comment = new Comment();
        comment.setContent(commentContent);
        post.add(comment);
        post.saveIt();
        URI targetURIForRedirection= URI.create("/posts/"+id);
        return Response.seeOther(targetURIForRedirection).build();
    }
}
