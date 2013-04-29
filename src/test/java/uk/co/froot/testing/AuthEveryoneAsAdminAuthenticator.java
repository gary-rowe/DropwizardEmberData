package uk.co.froot.testing;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import uk.co.froot.demo.ember.api.security.Authority;
import uk.co.froot.demo.ember.api.security.User;

import java.util.Set;

/**
 * <p>Authenticator to provide the following to dropwizard resource integration tests:</p>
 * <ul>
 * <li>Authentication as a superuser</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class AuthEveryoneAsAdminAuthenticator implements Authenticator<BasicCredentials,User> {
  @Override
  public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
    User user = new User();

    Set<Authority> authorities = Sets.newHashSet();
    authorities.add(Authority.ROLE_ADMIN);
    user.setAuthorities(authorities);

    user.setUserName("<test-admin>");

    return Optional.of(user);
  }
}
