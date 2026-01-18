/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link ArgumentHasAttributeException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument has undesirably a given attribute.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public final class ArgumentHasAttributeException extends AbstractInvalidArgumentException {
  /**
   * Creates a new {@link ArgumentHasAttributeException} for the given argument
   * and attributeName.
   * 
   * @param argument
   * @param attributeName
   * @throws RuntimeException if the given attributeName is null or blank.
   */
  private ArgumentHasAttributeException(final Object argument, final String attributeName) {
    super(argument, new ErrorPredicateDto("has a " + getValidatedAttributeNameFromAttributeName(attributeName)));
  }

  /**
   * @param argument
   * @param attributeName
   * @return a new {@link ArgumentHasAttributeException} for the given argument
   *         and attributeName.
   * @throws RuntimeException if the given attributeName is null or blank.
   */
  public static ArgumentHasAttributeException forArgumentAndAttributeName(
    final Object argument,
    final String attributeName) {
    return new ArgumentHasAttributeException(argument, attributeName);
  }

  /**
   * @param attributeName
   * @return a validated attribute name from the given attribtueName.
   * @throws RuntimeException if the given attributeName is null or blank.
   */
  private static String getValidatedAttributeNameFromAttributeName(final String attributeName) {
    if (attributeName == null) {
      throw new IllegalArgumentException("The given attribute name is null.");
    }

    if (attributeName.isBlank()) {
      throw new IllegalArgumentException("The given attribute name is blank.");
    }

    return attributeName;
  }
}
