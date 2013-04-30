package uk.co.froot.demo.ember.api.blog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>Wrapper to provide the following to API:</p>
 * <ul>
 * <li>Provides pagination metadata for a collection of posts</li>
 * </ul>
 * </p>
 */
public class PostList {

  @JsonProperty
  private List<Post> posts = Lists.newArrayList();


}
