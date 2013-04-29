package uk.co.froot.demo.ember.auth.openid;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import uk.co.froot.demo.ember.api.security.User;
import uk.co.froot.demo.ember.auth.InMemoryUserCache;

/**
 * <p>Authenticator to provide the following to application:</p>
 * <ul>
 * <li>Verifies the provided credentials are valid</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class OpenIDAuthenticator implements Authenticator<OpenIDCredentials, User> {

  private final InMemoryUserCache cache;

  @Inject
  public OpenIDAuthenticator(InMemoryUserCache cache) {
    this.cache = cache;
  }

  @Override
  public Optional<User> authenticate(OpenIDCredentials credentials) throws AuthenticationException {

    // Get the User referred to by the API key
    Optional<User> user = cache
      .getBySessionId(credentials.getSessionId());
    if (!user.isPresent()) {
      return Optional.absent();
    }

    // Check that their authorities match their credentials
    if (!user.get().hasAllAuthorities(credentials.getRequiredAuthorities())) {
      return Optional.absent();
    }

    return user;

  }

}
