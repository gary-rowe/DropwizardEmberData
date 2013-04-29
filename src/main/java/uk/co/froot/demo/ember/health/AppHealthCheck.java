package uk.co.froot.demo.ember.health;

/**
 * <p>HealthCheck to provide the following to application:</p>
 * <ul>
 * <li>Provision of checks against a given Configuration property </li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */

import com.yammer.metrics.core.HealthCheck;

public class AppHealthCheck extends HealthCheck {

  public AppHealthCheck() {
    super("OpenID Demo");
  }

  @Override
  protected Result check() throws Exception {
    return Result.healthy();
  }
}