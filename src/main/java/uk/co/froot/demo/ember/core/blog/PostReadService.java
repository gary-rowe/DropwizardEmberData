package uk.co.froot.demo.ember.core.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.froot.demo.ember.api.blog.PostList;

/**
 * <p>Read service to provide the following to resources:</p>
 * <ul>
 * <li>Provision of API representations</li>
 * </ul>
 * </p>
 */
public class PostReadService {

  /**
   * Provides logging for this class
   */
  private static final Logger log = LoggerFactory.getLogger(PostReadService.class);

  public PostList all() {

    return new PostList();
  }

}
