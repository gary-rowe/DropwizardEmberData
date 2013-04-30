package uk.co.froot.demo.ember.api.blog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.collect.Lists;
import com.yammer.dropwizard.json.JsonSnakeCase;

import java.util.List;

/**
 * <p>Wrapper to provide the following to API:</p>
 * <ul>
 * <li>Provides pagination metadata for a collection of posts</li>
 * </ul>
 */
@JsonSnakeCase
@JsonRootName("posts")
public class PostList {

  // TODO Add pagination support

  @JsonProperty
  private List<Post> posts = Lists.newArrayList();

  // TODO Consider sideloading comments etc

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }
}
