package uk.co.froot.demo.ember.core.blog;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import uk.co.froot.demo.ember.api.blog.Post;
import uk.co.froot.demo.ember.api.blog.PostList;

import java.util.Collections;
import java.util.Map;

/**
 * <p>Cache to provide the following to {@link Post}:</p>
 * <ul>
 * <li>In-memory singleton cache for Post instances</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class InMemoryPostCache {

  // A lot of threads will hit this cache
  private volatile Cache<String, Post> cache;

  InMemoryPostCache() {
    // Build the cache
    cache = CacheBuilder
      .newBuilder()
      .build();
  }

  public PostList all() {

    Map<String, Post> cacheMap = cache.asMap();

    // Build a post list out of the cache entries
    PostList postList = new PostList();
    for (Map.Entry<String, Post> postEntry : cacheMap.entrySet()) {
      Post clone = clone(postEntry.getValue(), postEntry.getValue().getId());
      postList.getPosts().add(clone);
    }

    Collections.sort(postList.getPosts());

    return postList;
  }

  /**
   * @return The matching Post or absent
   */
  public Optional<Post> find(String id) {

    Optional<Post> optional = Optional.fromNullable(cache.getIfPresent(id));

    if (optional.isPresent()) {
      Post clone = clone(optional.get(),optional.get().getId());
      return Optional.of(clone);
    }

    return optional;
  }

  /**
   * @param post   The Post to cache
   */
  public Post create(Post post) {
    Preconditions.checkNotNull(post);
    Preconditions.checkState(post.getId() == null);

    long id = cache.size()+1;

    Post cachePost = clone(post, id);

    cache.put(String.valueOf(id), cachePost);

    post.setId(id);

    // Return the provided not the cache copy to prevent reference leakage
    return post;
  }

  /**
   * @param post   The client Post to cache
   */
  public Post update(Post post) {
    Preconditions.checkNotNull(post);
    Preconditions.checkNotNull(post.getId());

    // Check if the post is present
    Optional<Post> optional = Optional.fromNullable(cache.getIfPresent(post.getId()));
    if (optional.isPresent()) {
      Post cachePost = optional.get();
      cachePost.setBody(post.getBody());
      cachePost.setTitle(post.getTitle());

      // Replace the cache
      cache.put(String.valueOf(post.getId()), post);

    }

    // Return the provided not the cache copy to prevent reference leakage
    return post;
  }

  private Post clone(Post post, long id) {
    Post cachePost = new Post();
    cachePost.setId(id);
    cachePost.setTitle(post.getTitle());
    cachePost.setSummary(post.getSummary());
    cachePost.setBody(post.getBody());
    return cachePost;
  }

}
