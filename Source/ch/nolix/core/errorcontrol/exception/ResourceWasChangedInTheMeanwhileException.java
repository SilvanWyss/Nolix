package ch.nolix.core.errorcontrol.exception;

/**
 * A {@link ResourceWasChangedInTheMeanwhileException} is a
 * {@link RuntimeException} that is supposed to be thrown when a specific
 * resource was undesirably changed in the meanwhile. For thhis conext, a
 * resource can be e.g.: data, schema, file, connection.
 * 
 * @author Silvan Wyss
 * @version 2022-02-27
 */
@SuppressWarnings("serial")
public final class ResourceWasChangedInTheMeanwhileException extends RuntimeException {

  /**
   * Creates a new {@link ResourceWasChangedInTheMeanwhileException} for the given
   * resource.
   * 
   * @param resource
   * @throws IllegalArgumentException if the given resource is null.
   * @throws IllegalArgumentException if the given resource is blank.
   */
  private ResourceWasChangedInTheMeanwhileException(final String resource) {
    super("The " + validateAndGetResource(resource) + " was changed in the meanwhile.");
  }

  /**
   * @param resource
   * @return a new {@link ResourceWasChangedInTheMeanwhileException} for the given
   *         resource.
   * @throws IllegalArgumentException if the given resource is null.
   * @throws IllegalArgumentException if the given resource is blank.
   */
  public static ResourceWasChangedInTheMeanwhileException forResource(final String resource) {
    return new ResourceWasChangedInTheMeanwhileException(resource);
  }

  /**
   * @param resource
   * @return a valid resource for the given resource.
   * @throws IllegalArgumentException if the given resource is null.
   * @throws IllegalArgumentException if the given resource is blank.
   */
  private static String validateAndGetResource(final String resource) {

    if (resource == null) {
      throw new IllegalArgumentException("The given resource is null.");
    }

    if (resource.isBlank()) {
      throw new IllegalArgumentException("The given resource is blank.");
    }

    return resource;
  }
}
