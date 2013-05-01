package uk.co.froot.ember.resources;

import com.google.common.base.Optional;
import com.sun.jersey.api.client.ClientResponse;
import com.yammer.dropwizard.testing.ResourceTest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.froot.demo.ember.api.blog.Post;
import uk.co.froot.demo.ember.api.blog.PostList;
import uk.co.froot.demo.ember.core.blog.PostReadService;
import uk.co.froot.demo.ember.resources.PostResource;
import uk.co.froot.ember.api.PostFaker;
import uk.co.froot.ember.api.PostListFaker;
import uk.co.froot.testing.AuthEveryoneAsAdminAuthenticator;
import uk.co.froot.testing.TestRestrictedToProvider;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PostResourceTest extends ResourceTest {

  private PostReadService postReadService = mock(PostReadService.class);

  @Override
  protected void setUpResources() throws Exception {

    PostList postList = PostListFaker.createPostList();
    Post post = PostFaker.createPostNoComments(3L);

    // Prepare mocks first
    when(postReadService.all()).thenReturn(postList);

    when(postReadService.find(1)).thenReturn(Optional.of(postList.getPosts().get(0)));
    when(postReadService.find(2)).thenReturn(Optional.of(postList.getPosts().get(1)));

    when(postReadService.create(any(Post.class))).thenReturn(post);
    when(postReadService.update(any(Post.class))).thenReturn(post);

    // Add resources
    addProvider(new TestRestrictedToProvider(new AuthEveryoneAsAdminAuthenticator()));
    addResource(new PostResource(postReadService));

  }

  @After
  public void tearDown() {
    reset(postReadService);
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void GET_findAll() {
    // Arrange

    // Act
    PostList postList = client()
      .resource("/posts")
      .get(PostList.class);

    // Assert
    assertThat(postList).isNotNull();

  }

  @Test
  public void GET_find() {
    // Arrange

    // Act
    Post post1 = client()
      .resource("/posts/1")
      .get(Post.class);

    // Assert
    assertThat(post1).isNotNull();

  }

  @Test
  public void POST_add_expectSuccess() {
    // Arrange
    Post post = PostFaker.createPostNoComments(null);
    post.setTitle("Test title");
    post.setBody("Test body");

    // Act
    ClientResponse response = client()
      .resource("/posts")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .post(ClientResponse.class, post);

    // Assert
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED_201);
    assertThat(response.getHeaders().get(HttpHeaders.LOCATION).get(0))
      .isEqualTo("/posts/3");

  }

  @Test
  public void PUT_update_expectSuccess() {
    // Arrange
    Post post = new Post();
    post.setId(3L);
    post.setTitle("Test title");
    post.setBody("Test body");

    // Act
    ClientResponse response = client()
      .resource("/posts")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .put(ClientResponse.class, post);

    // Assert
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT_204);

  }

}

