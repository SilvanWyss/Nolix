package ch.nolix.core.errorcontrol.invalidargumentexception;

import java.util.NoSuchElementException;

/**
 * A {@link ArgumentDoesNotHaveAttributeException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument does undesirably not have a given attribute.
 * 
 * @author Silvan Wyss
 * @version 2019-01-31
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotHaveAttributeException extends InvalidArgumentException {

  private static final String DEFAULT_ATTRIBUTE_NAME = "attribute";

  /**
   * Creates a new {@link ArgumentDoesNotHaveAttributeException} for the given
   * argument and attributeType.
   * 
   * @param argument      - Can be null.
   * @param attributeType
   * @throws IllegalArgumentException if the given attributeType is null.
   */
  private ArgumentDoesNotHaveAttributeException(final Object argument, final Class<?> attributeType) {
    super(argument, DEFAULT_ARGUMENT_NAME, "does not have a " + getNameOfAttributeType(attributeType));
  }

  /**
   * Creates a new {@link ArgumentDoesNotHaveAttributeException} for the given
   * argument and attributeName.
   * 
   * @param argument      - Can be null.
   * @param attributeName
   * @throws IllegalArgumentException if the given attributeName is null or blank.
   */
  private ArgumentDoesNotHaveAttributeException(final Object argument, final String attributeName) {
    super(
      argument,
      DEFAULT_ARGUMENT_NAME,
      "does not have a " + getValidatedAttributeNameFromAttributeName(attributeName));
  }

  /**
   * Creates a new {@link ArgumentDoesNotHaveAttributeException} for the given
   * argument, argumentName and attributeType.
   * 
   * @param argument      - Can be null.
   * @param argumentName
   * @param attributeType
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given attributeType is null.
   */
  private ArgumentDoesNotHaveAttributeException(
    final Object argument,
    final String argumentName,
    final Class<?> attributeType) {
    super(argument, argumentName, "does not have a " + getNameOfAttributeType(attributeType));
  }

  /**
   * Creates a new {@link ArgumentDoesNotHaveAttributeException} for the given
   * argument, argumentName and attributeName.
   * 
   * @param argument      - Can be null.
   * @param argumentName
   * @param attributeName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given attributeName is null or blank.
   */
  private ArgumentDoesNotHaveAttributeException(
    final Object argument,
    final String argumentName,
    final String attributeName) {
    super(argument, argumentName, "does not have a " + getValidatedAttributeNameFromAttributeName(attributeName));
  }

  /**
   * @param argument      - Can be null.
   * @param attributeName
   * @return a new {@link ArgumentDoesNotHaveAttributeException} for the given
   *         argument and attributeName.
   * @throws IllegalArgumentException if the given attributeName is null or blank.
   */
  public static ArgumentDoesNotHaveAttributeException forArgumentAndAttributeName(
    final Object argument,
    final String attributeName) {
    return new ArgumentDoesNotHaveAttributeException(argument, attributeName);
  }

  /**
   * @param argument      - Can be null.
   * @param attributeType
   * @return a new {@link ArgumentDoesNotHaveAttributeException} for the given
   *         argument and attributeType.
   * @throws IllegalArgumentException if the given attributeType is null.
   */
  public static ArgumentDoesNotHaveAttributeException forArgumentAndAttributeType(
    final Object argument,
    final Class<?> attributeType) {
    return new ArgumentDoesNotHaveAttributeException(argument, attributeType);
  }

  /**
   * @param argument      - Can be null.
   * @param argumentName
   * @param attributeName
   * @return a new {@link ArgumentDoesNotHaveAttributeException} for the given
   *         argument, argumentName and attributeName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given attributeName is null or blank.
   */
  public static ArgumentDoesNotHaveAttributeException forArgumentAndArgumentNameAndAttributeName(
    final Object argument,
    final String argumentName,
    final String attributeName) {
    return new ArgumentDoesNotHaveAttributeException(argument, argumentName, attributeName);
  }

  /**
   * @param argument      - Can be null.
   * @param argumentName
   * @param attributeType
   * @return a new {@link ArgumentDoesNotHaveAttributeException} for the given
   *         argument, argumentName and attributeType.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given attributeType is null.
   */
  public static ArgumentDoesNotHaveAttributeException forArgumentAndArgumentNameAndAttributeType(
    final String argumentName,
    final Object argument,
    final Class<?> attributeType) {
    return new ArgumentDoesNotHaveAttributeException(argument, argumentName, attributeType);
  }

  /**
   * @param attributeType
   * @return the name of the given attribtueType.
   * @throws IllegalArgumentException if the given attribtueType is null.
   */
  private static String getNameOfAttributeType(final Class<?> attributeType) {

    if (attributeType == null) {
      throw new IllegalArgumentException("The given attribute type is null.");
    }

    final var name = attributeType.getSimpleName();

    if (name != null && !name.isEmpty()) {
      return name;
    }

    return DEFAULT_ATTRIBUTE_NAME;
  }

  /**
   * @param attributeName
   * @return a validated attribute name from the given attributeName.
   * @throws IllegalArgumentException if the given attributeName is null or blank.
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

  /**
   * @return a new {@link NoSuchElementException} representation of the current
   *         {@link ArgumentDoesNotHaveAttributeException}.
   */
  public NoSuchElementException toNoSuchElementException() {
    return new NoSuchElementException(getMessage());
  }
}
