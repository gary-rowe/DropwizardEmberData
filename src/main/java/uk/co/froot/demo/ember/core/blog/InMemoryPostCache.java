package uk.co.froot.demo.ember.core.blog;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import uk.co.froot.demo.ember.api.blog.Post;

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

  /**
   * @return The matching ClientPost or absent
   */
  public Optional<Post> get(String postId) {

    return Optional.fromNullable(cache.getIfPresent(postId));

  }

  /**
   * @param postId The ID to use to locate the Post
   * @param post   The client Post to cache
   */
  public void put(String postId, Post post) {
    Preconditions.checkNotNull(post);
    cache.put(postId, post);
  }

}
