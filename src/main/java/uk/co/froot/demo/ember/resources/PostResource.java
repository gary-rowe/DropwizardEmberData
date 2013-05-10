package uk.co.froot.demo.ember.resources;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import uk.co.froot.demo.ember.api.blog.Post;
import uk.co.froot.demo.ember.api.blog.PostList;
import uk.co.froot.demo.ember.core.blog.PostReadService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public home page</li>
 * </ul>
 * <p>This maps to the Ember Data REST requirements as follows:</p>
 * <pre>
 * Find      GET     /posts/123
 * Find All  GET     /posts
 * Update    PUT     /posts/123
 * Create    POST    /posts
 * Delete    DELETE  /posts/123
 * </pre>
 *
 * @since 0.0.1
 */
@Path("/posts")
@Produces("application/json; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource extends BaseResource {

  private final PostReadService service;

  @Inject
  public PostResource(PostReadService service) {
    this.service = service;

  }

  /**
   * Create a single post by ID
   *
   * @return A response with a suitable Location
   */
  @POST
  @Timed
  @CacheControl(noCache = true)
  public Response add(@NotNull Post post) {

    if (post.getId() != null
      || post.getTitle() == null || post.getTitle().trim().isEmpty()
      || post.getSummary() == null || post.getSummary().trim().isEmpty()
      || post.getBody() == null || post.getBody().trim().isEmpty()
      ) {
      throw badRequest();
    }

    Post updatedPost = service.update(post);

    URI location = UriBuilder
      .fromPath("/{id}")
      .build(updatedPost.getId());

    return Response.created(location).build();

  }

  /**
   * Retrieve all posts
   *
   * @return A paginated representation of all posts
   */
  @GET
  @Timed
  @CacheControl(noCache = true)
  public PostList findAll() {

    return service.all();

  }

  /**
   * Retrieve a single post by ID
   *
   * @return The single post
   */
  @GET
  @Timed
  @Path("/{id}")
  @CacheControl(noCache = true)
  public Post find(
    @PathParam("id") Integer id) {

    Optional<Post> optional = service.find(id);

    if (optional.isPresent()) {
      return optional.get();
    }

    throw notFound();

  }

  /**
   * Update a single post by ID
   *
   * @return A response with a suitable Location
   */
  @PUT
  @Timed
  @CacheControl(noCache = true)
  public Response update(@NotNull Post post) {

    if (post.getId() == null
      || post.getTitle() == null || post.getTitle().trim().isEmpty()
      || post.getSummary() == null || post.getSummary().trim().isEmpty()
      || post.getBody() == null || post.getBody().trim().isEmpty()
      ) {
      throw badRequest();
    }

    if (service.update(post) != null) {
      return Response.noContent().build();
    }

    throw badRequest();

  }

}
