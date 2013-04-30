package uk.co.froot.demo.ember.resources;

import com.google.inject.Inject;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import uk.co.froot.demo.ember.api.blog.PostList;
import uk.co.froot.demo.ember.core.blog.PostReadService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public home page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class PostResource extends BaseResource {

  private final PostReadService service;

  @Inject
  public PostResource(PostReadService service) {
    this.service = service;
  }

  /**
   * Provide all posts
   *
   * @return An OK response (should really be HAL links)
   */
  @GET
  @Timed
  @CacheControl(noCache = true)
  public Response getHome() {

    return Response.ok().build();
  }

  /**
   * Provide all posts
   *
   * @return A paginated representation of all posts
   */
  @GET
  @Path("/posts")
  @Timed
  @CacheControl(noCache = true)
  public PostList getAll() {

    return service.all();
  }

}
