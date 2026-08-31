/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import ch.nolix.base.validation.base.AbstractMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link AbstractStringMediator} is a {@link AbstractMediator} for an
 * argument that is a {@link String}. A {@link AbstractStringMediator} is not
 * mutable.
 * 
 * @author Silvan Wyss
 */
public abstract class AbstractStringMediator extends AbstractObjectMediator<String> {
  /**
   * Creates a new {@link AbstractStringMediator} for the given argument.
   * 
   * @param argument
   */
  protected AbstractStringMediator(final String argument) {
    super(argument);
  }

  /**
   * Creates a new {@link AbstractStringMediator} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank
   */
  protected AbstractStringMediator(final String argument, final String argumentName) {
    super(argument, argumentName);
  }

  /**
   * @param length
   * @throws RuntimeException if the given length is negative
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} does not have the
   *                          given length.
   */
  public void hasLength(final int length) {
    // Asserts that the given length is not negative.
    if (length < 0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(length, LowerCaseVariableNameCatalog.LENGTH);
    }

    // Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    // Asserts that the argument of the current StringMediator does not have the
    // given length.
    if (getStoredArgument().length() != length) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not have the length " + length);
    }
  }

  /**
   * @throws RuntimeException          if the argument of the current
   *                                   {@link AbstractStringMediator} is null
   * @throws NonEmptyArgumentException if the argument of the current
   *                                   {@link AbstractStringMediator} is not
   *                                   empty.
   */
  public void isEmpty() {
    // Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    // Asserts that the argument of the current StringMediator is not empty.
    if (!getStoredArgument().isEmpty()) {
      throw NonEmptyArgumentException.forArgumentAndArgumentName(getStoredArgument(), getArgumentName());
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is empty
   */
  public void isNotEmpty() {
    // Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    // Asserts that the argument of the current StringMediator is not empty.
    if (getStoredArgument().isEmpty()) {
      throw EmptyArgumentException.forArgumentAndArgumentName(getStoredArgument(), getArgumentName());
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is blank
   */
  public void isNotBlank() {
    // Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    // Asserts that the the argument of the current StringMediator is not blank.
    if (getStoredArgument().isBlank()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is blank");
    }
  }

  /**
   * @param maxLength
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is longer than the
   *                          given max length says.
   */
  public void isNotLongerThan(final int maxLength) {
    // Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    // Asserts that the argument of the current StringMediator is not longer than
    // the given max length says.
    if (getStoredArgument().length() > maxLength) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is longer than " + maxLength);
    }
  }

  /**
   * @param minLength
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is shorter than the
   *                          given min length says.
   */
  public void isNotShorterThan(final int minLength) {
    // Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    // Asserts that the argument of the current StringMediator is not shorter than
    // the given min length says.
    if (getStoredArgument().length() < minLength) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "has the length " + getStoredArgument().length() + " and is therefore shorter than " + minLength);
    }
  }

  /**
   * @param regularExpression
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} does not match the
   *                          given regularExpression.
   */
  public void matches(final String regularExpression) {
    // Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    // Asserts that the argument of the current StringMediator matches the given
    // regularExpression.
    if (!getStoredArgument().matches(regularExpression)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not match the regular expression '" + regularExpression + "'");
    }
  }

  /**
   * @param prefix
   * @throws RuntimeException if the given prefix is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractStringMediator} does not start with
   *                          the given prefix.
   */
  public void startsWith(final String prefix) {
    isNotNull();

    if (!getStoredArgument().startsWith(prefix)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not start with the prefix '" + prefix + "'");
    }
  }
}
