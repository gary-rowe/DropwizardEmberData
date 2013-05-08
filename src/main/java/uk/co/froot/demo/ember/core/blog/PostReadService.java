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

  // Default data based on Lorem Ipsum
  private static final String LOREM_SUMMARY = "\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nec quam id sapien elementum aliquet. Vestibulum ultrices mollis feugiat. Sed bibendum imperdiet enim nec accumsan. In quis libero ac leo scelerisque congue. Duis felis nunc, dignissim vel volutpat nec, placerat ac metus. Aliquam eget ipsum ut ligula condimentum elementum. Sed in dolor et tortor tincidunt aliquet.\n";

  private static final String LOREM_BODY = "\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nec quam id sapien elementum aliquet. Vestibulum ultrices mollis feugiat. Sed bibendum imperdiet enim nec accumsan. In quis libero ac leo scelerisque congue. Duis felis nunc, dignissim vel volutpat nec, placerat ac metus. Aliquam eget ipsum ut ligula condimentum elementum. Sed in dolor et tortor tincidunt aliquet.\n" +
    "\n" +
    "Suspendisse magna nibh, laoreet ut ultricies id, scelerisque quis enim. Curabitur vel leo a sem viverra blandit. Praesent in leo eu ipsum placerat viverra. Vivamus vel velit nec velit pretium tempor. Fusce cursus varius metus at rutrum. Maecenas a dolor velit. Donec varius eros eget lacus faucibus congue. Phasellus eget diam id magna mollis ultrices quis eu arcu. Etiam sed ligula augue, vitae varius ipsum. Morbi quam urna, scelerisque in tempor et, porttitor vel tortor. Proin tristique vulputate lacus euismod commodo. Cras fringilla lobortis mauris, in sollicitudin risus egestas at.\n" +
    "\n" +
    "Morbi pulvinar tortor tincidunt turpis auctor a convallis felis convallis. Nullam blandit adipiscing urna, sed mattis erat auctor tempor. Nunc erat nulla, pulvinar vel tristique ornare, porta ac erat. Morbi vestibulum enim at lectus sodales tempus venenatis leo sodales. Nullam sit amet aliquam erat. Aliquam fermentum, neque eu interdum faucibus, eros odio eleifend ligula, non egestas mauris arcu vel mi. Maecenas et lacus ipsum, vitae mollis nibh. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis ligula nibh, cursus in blandit id, rhoncus vel nulla. Aenean dolor ligula, vehicula id luctus suscipit, molestie in ante. Sed consectetur neque ac tortor molestie tempus.\n" +
    "\n" +
    "Sed ac ultricies mauris. Cras ac nulla libero, id faucibus sapien. Nulla imperdiet commodo neque, non convallis felis posuere at. Sed vestibulum aliquet luctus. Etiam fringilla leo quis orci imperdiet vitae consectetur mauris malesuada. Nunc fermentum erat ut sapien dignissim eu pharetra diam accumsan. Aliquam in aliquet nulla. Suspendisse malesuada velit ut nunc malesuada ut lobortis neque dapibus.\n" +
    "\n" +
    "Nulla nibh dolor, ultrices at tincidunt vitae, pulvinar eget nunc. Nam a elit sit amet lorem gravida auctor eget eget augue. Sed ut felis eu velit pulvinar venenatis. Mauris nulla quam, rhoncus eget varius a, volutpat at orci. Suspendisse ut mi ligula, et fringilla lorem. Donec eget metus eu est vestibulum convallis ac consequat leo. Nunc vel arcu ac lorem consequat faucibus vitae sed massa. Praesent vitae odio est, a pulvinar elit. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. ";


  @Inject
  public PostReadService(InMemoryPostCache postCache) {
    this.postCache = postCache;

    // Initialise with a bunch of lorem ipsum
    for (int i = 1; i<100; i++) {
      final Post post = new Post();
      post.setTitle("Title "+i);
      post.setSummary("Summary "+i+LOREM_SUMMARY);
      post.setBody("Body "+i+LOREM_BODY);

      // Persist into the cache
      create(post);
    }

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
