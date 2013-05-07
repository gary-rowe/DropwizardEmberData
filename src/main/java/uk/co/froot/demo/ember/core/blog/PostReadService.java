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
    post1.setTitle("Title 1");
    post1.setSummary("Summary 1");
    post1.setBody("Body 1");

    final Post post2 = new Post();
    post2.setTitle("Title 2");
    post2.setSummary("Summary 2");
    post2.setBody("Body 2");

    // Persist them into the cache
    create(post1);
    create(post2);

  }

  public PostList all() {
    return postCache.all();
  }

  public Optional<Post> find(Integer id) {
    return postCache.find(String.valueOf(id));
  }

  public Post create(Post post) {
    return postCache.create(post);
  }

  public Post update(Post post) {
    return postCache.update(post);
  }
}
