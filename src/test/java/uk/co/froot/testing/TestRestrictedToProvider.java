package uk.co.froot.testing;

import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import com.yammer.dropwizard.auth.Authenticator;
import org.junit.Ignore;
import uk.co.froot.demo.ember.auth.annotation.RestrictedTo;

/**
 * <p>Authentication provider to provide the following to Jersey:</p>
 * <ul>
 * <li>Bridge between Dropwizard and Jersey for Test authentication</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Ignore
public class TestRestrictedToProvider implements InjectableProvider<RestrictedTo, Parameter> {

  private final Authenticator authenticator;

  /**
   * Creates a new {@link TestRestrictedToProvider} with the given {@link com.yammer.dropwizard.auth.Authenticator} and realm.
   *
   * @param authenticator the authenticator which will take the credentials and
   *                      convert them into instances of {@code T}
   */
  public TestRestrictedToProvider(Authenticator authenticator) {
    this.authenticator = authenticator;
  }

  @Override
  public ComponentScope getScope() {
    return ComponentScope.PerRequest;
  }

  @Override
  public Injectable<?> getInjectable(ComponentContext ic,
                                     RestrictedTo a,
                                     Parameter c) {
    return new TestRestrictedToInjectable(authenticator, a.value());
  }
}