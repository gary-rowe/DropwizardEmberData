package uk.co.froot.ember.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.yammer.dropwizard.json.ObjectMapperFactory;
import com.yammer.dropwizard.testing.ResourceTest;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.froot.demo.ember.api.blog.PostList;
import uk.co.froot.demo.ember.core.blog.PostReadService;
import uk.co.froot.demo.ember.resources.PostResource;
import uk.co.froot.testing.AuthEveryoneAsAdminAuthenticator;
import uk.co.froot.testing.TestRestrictedToProvider;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PostResourceTest extends ResourceTest {

  private PostReadService postReadService = mock(PostReadService.class);

  @Override
  protected ObjectMapperFactory getObjectMapperFactory() {

    ObjectMapperFactory objectMapperFactory = new ObjectMapperFactory();
    objectMapperFactory.enable(SerializationFeature.WRAP_ROOT_VALUE);
    return objectMapperFactory;
  }

  @Override
  protected void setUpResources() throws Exception {
    addProvider(new TestRestrictedToProvider(new AuthEveryoneAsAdminAuthenticator()));
    addResource(new PostResource(postReadService));

    when(postReadService.all()).thenReturn(new PostList());
  }

  @After
  public void tearDown() {
    reset(postReadService);
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void GET_Posts() {
    // Arrange
    int expectedPage = 0;
    int expectedPageSize = 10;

    // Act
    PostList postList = client()
      .resource("/posts")
      .queryParam("page", "0").queryParam("pageSize", "10")
      .get(PostList.class);

    // Assert
    assertThat(postList).isNotNull();

    verify(postReadService, times(1)).all();
    verifyNoMoreInteractions(postReadService);
  }

}

