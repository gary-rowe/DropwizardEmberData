package uk.co.froot.testing;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import uk.co.froot.demo.ember.api.security.Authority;
import uk.co.froot.demo.ember.api.security.User;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Set;

/**
 * <p>Injectable to provide the following to {@link TestRestrictedToProvider}:</p>
 * <ul>
 * <li>Performs decode from HTTP request</li>
 * <li>Carries test authentication data</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class TestRestrictedToInjectable extends AbstractHttpContextInjectable<User> {

  private final Authenticator<BasicCredentials, User> authenticator;
  private final Set<Authority> requiredAuthorities;

  /**
   * @param authenticator The Authenticator that will compare credentials
   * @param requiredAuthorities The required authorities as provided by the RestrictedTo annotation
   */
  TestRestrictedToInjectable(
    Authenticator authenticator,
    Authority[] requiredAuthorities) {
    this.authenticator = authenticator;
    this.requiredAuthorities = Sets.newHashSet(Arrays.asList(requiredAuthorities));
  }

  @Override
  public User getValue(HttpContext httpContext) {
    try {
      Optional<User> userOptional = authenticator.authenticate(new BasicCredentials("username", "password"));
      return userOptional.get();
    } catch (AuthenticationException e) {
      e.printStackTrace();
    }
    throw new WebApplicationException(Response.Status.UNAUTHORIZED);
  }

}
