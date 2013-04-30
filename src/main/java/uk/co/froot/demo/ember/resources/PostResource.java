package uk.co.froot.demo.ember.resources;

import com.google.inject.Inject;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import uk.co.froot.demo.ember.api.blog.Post;
import uk.co.froot.demo.ember.api.blog.PostList;
import uk.co.froot.demo.ember.core.blog.PostReadService;

import javax.validation.constraints.Digits;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
@Produces(MediaType.APPLICATION_JSON)
public class PostResource extends BaseResource {

  private final PostReadService service;

  @Inject
  public PostResource(PostReadService service) {
    this.service = service;

  }
    /**
     * Provide all posts
     *
     * @return A paginated representation of all posts
     */
    @GET
    @Timed
    @CacheControl(noCache = true)
    public PostList findAll () {

      return service.all();
    }

    /**
     * Provide all posts
     *
     * @return A paginated representation of all posts
     */
    @GET
    @Timed
    @Path("/{id}")
    @CacheControl(noCache = true)
    public Post find (
      @PathParam("id")@Digits(integer = 5, fraction = 0)Integer id){

      return service.find(id).get();
    }

  }
