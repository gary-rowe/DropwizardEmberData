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

    // Initialise with a couple of basic posts for now
    final Post post1 = new Post();
    post1.setId(1);
    post1.setTitle("Post 1");
    post1.setBody("Post 1 body");

    final Post post2 = new Post();
    post2.setId(2);
    post2.setTitle("Post 2");
    post2.setBody("Post 2 body");

    // Persist them into the cache
    put(post1);
    put(post2);

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
