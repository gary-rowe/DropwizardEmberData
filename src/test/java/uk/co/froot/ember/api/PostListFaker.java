package uk.co.froot.ember.api;

import uk.co.froot.demo.ember.api.blog.Post;
import uk.co.froot.demo.ember.api.blog.PostList;

/**
 * <p>Faker to provide implementations of {@link PostList} for use in test cases</p>
 *
 * See the <a href="https://github.com/DiUS/java-faker">GitHub Java Faker project</a> for more details
 */
public class PostListFaker {

  /**
   * @return A post list with two default entries
   */
  public static PostList createPostList() {

    // Initialise with a couple of basic posts
    final Post post1 = PostFaker.createPostNoComments(1L);
    final Post post2 = PostFaker.createPostNoComments(2L);

    PostList postList = new PostList();
    postList.getPosts().add(post1);
    postList.getPosts().add(post2);

    return postList;
  }


}

