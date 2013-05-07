package uk.co.froot.ember.api;

import uk.co.froot.demo.ember.api.blog.Post;

/**
 * <p>Faker to provide implementations of {@link uk.co.froot.demo.ember.api.blog.PostList} for use in test cases</p>
 * <p/>
 * See the <a href="https://github.com/DiUS/java-faker">GitHub Java Faker project</a> for more details
 */
public class PostFaker {

  /**
   * @return A post list with two default entries
   */
  public static Post createPostNoComments(Long id) {

    // Initialise with a couple of basic posts for now
    final Post post = new Post();
    post.setId(id);

    if (id != null) {
      post.setTitle("Title " + id);
      post.setSummary("Summary " + id);
      post.setBody("Body " + id);
    } else {
      post.setTitle("Title");
      post.setSummary("Summary");
      post.setBody("Body");
    }

    return post;
  }


}

