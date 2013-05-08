package uk.co.froot.demo.ember.api.blog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
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
@JsonRootName("post")
public class Post implements Comparable<Post> {

  @JsonProperty
  private Long id;

  @JsonProperty
  private String title;

  @JsonProperty
  private String summary;

  @JsonProperty
  private String body;

  @JsonProperty
  private List<Long> commentIds = Lists.newArrayList();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Long> getCommentIds() {
    return commentIds;
  }

  public void setCommentIds(List<Long> commentIds) {
    this.commentIds = commentIds;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Post post = (Post) o;

    return !(id != null ? !id.equals(post.id) : post.id != null);

  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public int compareTo(Post that) {
    return this.getId().compareTo(that.getId());
  }
}
