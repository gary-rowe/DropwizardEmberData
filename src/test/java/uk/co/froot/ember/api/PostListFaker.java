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
   *
   * @return A post list with two default entries
   */
  public static PostList createPostList() {

    // Initialise with a couple of basic posts for now
    final Post post1 = new Post();
    post1.setId(1);
    post1.setTitle("Post 1");
    post1.setBody("Post 1 body");

    final Post post2 = new Post();
    post2.setId(2);
    post2.setTitle("Post 2");
    post2.setBody("Post 2 body");

    PostList postList = new PostList();
    postList.getPosts().add(post1);
    postList.getPosts().add(post2);

    return postList;
  }


}

