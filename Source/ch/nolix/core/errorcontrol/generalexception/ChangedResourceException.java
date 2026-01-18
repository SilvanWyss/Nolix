/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.generalexception;

/**
 * A {@link ChangedResourceException} is a {@link RuntimeException} that is
 * supposed to be thrown when a specific resource was undesirably changed in the
 * meanwhile. For thhis conext, a resource can be e.g.: data, schema, file,
 * connection.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public final class ChangedResourceException extends RuntimeException {
  /**
   * Creates a new {@link ChangedResourceException} for the given resource.
   * 
   * @param resource
   * @throws IllegalArgumentException if the given resource is null.
   * @throws IllegalArgumentException if the given resource is blank.
   */
  private ChangedResourceException(final String resource) {
    super("The " + validateAndGetResource(resource) + " was changed in the meanwhile.");
  }

  /**
   * @param resource
   * @return a new {@link ChangedResourceException} for the given resource.
   * @throws IllegalArgumentException if the given resource is null.
   * @throws IllegalArgumentException if the given resource is blank.
   */
  public static ChangedResourceException forResource(final String resource) {
    return new ChangedResourceException(resource);
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
