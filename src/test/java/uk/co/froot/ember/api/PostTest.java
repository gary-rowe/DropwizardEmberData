package uk.co.froot.ember.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;
import com.yammer.dropwizard.testing.FixtureHelpers;
import com.yammer.dropwizard.testing.JsonHelpers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.froot.demo.ember.api.blog.Post;
import uk.co.froot.testing.FixtureAsserts;

import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

public class PostTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void verifySerialization() throws IOException {

    // Arrange
    ObjectMapper objectMapper = new ObjectMapperFactory().build();

    // Act
    Post testObject = objectMapper.readValue(FixtureHelpers.fixture("fixtures/post/test-post-1.json"),Post.class);

    // Assert
    assertThat(testObject.getId()).isEqualTo(1);

    // Marshal
    FixtureAsserts.assertStringMatchesJsonFixture(
      "Post",
      JsonHelpers.asJson(testObject),
      "/fixtures/post/test-post-1.json");

  }

}
