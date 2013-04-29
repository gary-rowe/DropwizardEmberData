package uk.co.froot.demo.ember.resources;

import com.sun.jersey.api.core.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * <p>Provider to provide the following to Jersey framework:</p>
 * <ul>
 * <li>Provision of exception to response mapping</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

  private static final Logger log = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

  @Context
  HttpContext httpContext;

  @Override
  public Response toResponse(RuntimeException runtime) {

    // Build default response
    Response defaultResponse = Response
      .serverError()
      .build();

    // Check for any specific handling
    if (runtime instanceof WebApplicationException) {

      return handleWebApplicationException(runtime, defaultResponse);
    }

    // Use the default
    log.error(runtime.getMessage(),runtime);
    return defaultResponse;

  }

  private Response handleWebApplicationException(RuntimeException exception, Response defaultResponse) {
    WebApplicationException webAppException = (WebApplicationException) exception;

    // No logging
    if (webAppException.getResponse().getStatus() == Response.Status.UNAUTHORIZED.getStatusCode()) {
      return Response
        .status(Response.Status.UNAUTHORIZED)
        .build();
    }
    if (webAppException.getResponse().getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .build();
    }

    // Debug logging

    // Warn logging

    // Error logging
    log.error(exception.getMessage(),exception);

    return defaultResponse;
  }

}
