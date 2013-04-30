package uk.co.froot.demo.ember.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import java.util.Locale;

/**
 * <p>Abstract base class to provide the following to subclasses:</p>
 * <ul>
 * <li>Provision of common methods</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public abstract class BaseResource {

  /**
   * Jersey creates a fresh resource every request so this is safe
   */
  @Context
  protected UriInfo uriInfo;

  /**
   * Jersey creates a fresh resource every request so this is safe
   */
  @Context
  protected HttpHeaders httpHeaders;

  public BaseResource() {

  }

  /**
   * @return The most appropriate locale for the upstream request (never null)
   */
  public Locale getLocale() {
    // TODO This should be a configuration setting
    Locale defaultLocale = Locale.UK;

    Locale locale;
    if (httpHeaders == null) {
      locale = defaultLocale;
    } else {
      locale = httpHeaders.getLanguage();
      if (locale == null) {
        locale = defaultLocale;
      }
    }
    return locale;
  }
}
