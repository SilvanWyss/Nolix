/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.util.Objects;
import java.util.function.Predicate;

import ch.nolix.base.validation.base.AbstractMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNotNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <A> the type of the argument of an {@link AbstractObjectMediator}.
 */
public abstract class AbstractObjectMediator<A> extends AbstractMediator {
  private final A argument;

  /**
   * Creates a new {@link AbstractObjectMediator} for the given argument.
   * 
   * @param argument
   */
  protected AbstractObjectMediator(final A argument) {
    this.argument = argument;
  }

  /**
   * Creates a new {@link AbstractObjectMediator} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  protected AbstractObjectMediator(final A argument, final String argumentName) {
    super(argumentName);

    this.argument = argument;
  }

  /**
   * @param condition
   * @throws RuntimeException if the given condition is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} does not fulfill the
   *                          given condition.
   */
  public final void fulfills(Predicate<A> condition) {
    if (condition == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.CONDITION);
    }

    if (!condition.test(getStoredArgument())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not fulfil the given condition");
    }
  }

  /**
   * @param object
   * @throws RuntimeException if the given object is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} does not have the
   *                          same {@link String} representation as the given
   *                          object.
   */
  public final void hasSameStringRepresentationAs(final Object object) {
    if (object == null) {
      throw ArgumentIsNullException.forArgumentType(Object.class);
    }

    final var stringRepresentation = object.toString();

    hasStringRepresentation(stringRepresentation);
  }

  /**
   * @param stringRepresentation
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} does not have the
   *                          given stringRepresentation.
   */
  public final void hasStringRepresentation(final String stringRepresentation) {
    isNotNull();

    final var actualStringRepresentation = getStoredArgument().toString();

    if (!Objects.equals(actualStringRepresentation, stringRepresentation)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not have the String representation '" + stringRepresentation + "'");
    }
  }

  /**
   * @param object
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} does not equal the
   *                          given object.
   */
  public final void isEqualTo(final Object object) {
    if (!Objects.equals(getStoredArgument(), object)) {
      throw UnequalArgumentException.forArgumentAndValue(argument, object);
    }
  }

  /**
   * @param object
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} is not (!) the given
   *                          object.
   */
  public final void is(final Object object) {
    if (argument != object) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not the given object");
    }
  }

  /**
   * @param object
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} is the given object.
   */
  public final void isNot(final Object object) {
    if (argument == object) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is the given object");
    }
  }

  /**
   * @param object
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} equals the given
   *                          object.
   */
  public final void isNotEqualTo(final A object) {
    if (Objects.equals(getStoredArgument(), object)) {
      throw EqualArgumentException.forArgumentAndEqualValue(argument, object);
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} is null
   */
  public final void isNotNull() {
    if (argument == null) {
      throw ArgumentIsNullException.forArgumentName(getArgumentName());
    }
  }

  /**
   * @throws ArgumentIsNotNullException if the argument of the current
   *                                    {@link AbstractObjectMediator} is not (!)
   *                                    null.
   */
  public final void isNull() {
    if (argument != null) {
      throw ArgumentIsNotNullException.forArgument(getStoredArgument());
    }
  }

  /**
   * @param type
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractObjectMediator} is not of the given
   *                          type.
   */
  public final void isOfType(final Class<?> type) {
    isNotNull();

    if (!type.isAssignableFrom(getStoredArgument().getClass())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not a " + type);
    }
  }

  /**
   * @return the argument of the current {@link AbstractObjectMediator}.
   */
  protected final A getStoredArgument() {
    return argument;
  }
}
