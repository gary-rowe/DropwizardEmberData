package uk.co.froot.demo.ember.api.blog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.yammer.dropwizard.json.JsonSnakeCase;

import java.util.List;

/**
 * <p>Public representation to provide the following to resources:</p>
 * <ul>
 * <li>Representation of a blog post</li>
 * </ul>
 * </p>
 */
@JsonSnakeCase
public class Post {

  @JsonProperty
  private Integer id;

  @JsonProperty
  private String title;

  @JsonProperty
  private String body;

  @JsonProperty
  private List<Integer> commentIds = Lists.newArrayList();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Integer> getCommentIds() {
    return commentIds;
  }

  public void setCommentIds(List<Integer> commentIds) {
    this.commentIds = commentIds;
  }

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
