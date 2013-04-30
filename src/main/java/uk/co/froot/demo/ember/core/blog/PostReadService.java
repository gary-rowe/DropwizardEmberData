package uk.co.froot.demo.ember.core.blog;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import uk.co.froot.demo.ember.api.blog.Post;
import uk.co.froot.demo.ember.api.blog.PostList;

/**
 * <p>Read service to provide the following to resources:</p>
 * <ul>
 * <li>Provision of API representations</li>
 * </ul>
 * </p>
 */
public class PostReadService {

  private final InMemoryPostCache postCache;

  @Inject
  public PostReadService(InMemoryPostCache postCache) {
    this.postCache = postCache;
  }

  public PostList all() {
    return postCache.all();
  }

  public Optional<Post> find(Integer id) {
    return postCache.find(String.valueOf(id));
  }

  public void put(Post post) {
    postCache.put(String.valueOf(post.getId()), post);
  }
}
