package uk.co.froot.demo.ember.api.blog;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Public representation to provide the following to resources:</p>
 * <ul>
 * <li>Representation of a blog post</li>
 * </ul>
 * </p>
 */
public class Post {

  @JsonProperty
  private String title;

  @JsonProperty
  private String body;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
