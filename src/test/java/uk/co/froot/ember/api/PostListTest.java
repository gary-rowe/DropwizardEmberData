package uk.co.froot.ember.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;
import com.yammer.dropwizard.testing.FixtureHelpers;
import com.yammer.dropwizard.testing.JsonHelpers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.froot.demo.ember.api.blog.PostList;
import uk.co.froot.testing.FixtureAsserts;

import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

public class PostListTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void verifySerialization() throws IOException {

    // Arrange
    ObjectMapper objectMapper = new ObjectMapperFactory().build();

    // Act
    PostList testObject = objectMapper.readValue(FixtureHelpers.fixture("fixtures/post/test-post-list.json"),PostList.class);

    // Assert
    assertThat(testObject.getPosts().size()).isEqualTo(2);

    // Post 1
    assertThat(testObject.getPosts().get(0).getId()).isEqualTo(1);
    assertThat(testObject.getPosts().get(0).getTitle()).isEqualTo("Title 1");

    // Post 2
    assertThat(testObject.getPosts().get(1).getId()).isEqualTo(2);
    assertThat(testObject.getPosts().get(1).getTitle()).isEqualTo("Title 2");

    // Marshal
    FixtureAsserts.assertStringMatchesJsonFixture(
      "PostList",
      JsonHelpers.asJson(testObject),
      "/fixtures/post/test-post-list.json");

  }

}
