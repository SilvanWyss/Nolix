/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.util.Objects;
import java.util.function.Predicate;

import ch.nolix.base.errorcontrol.validator.Mediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNotNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A {@link ObjectMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @param <A> is the type of the argument of an {@link ObjectMediator}.
 */
public class ObjectMediator<A> extends Mediator {
  private final A argument;

  /**
   * Creates a new {@link ObjectMediator} for the given argument.
   * 
   * @param argument
   */
  protected ObjectMediator(final A argument) {
    //Calls other constructor.
    this(DEFAULT_ARGUMENT_NAME, argument);
  }

  /**
   * Creates a new {@link ObjectMediator} for the given argument, which has the
   * given argumentName.
   * 
   * @param argumentName
   * @param argument
   * @throws RuntimeException if the given argumentName is null.
   * @throws RuntimeException if the given argument name is blank.
   */
  protected ObjectMediator(final String argumentName, final A argument) {
    //Calls constructor of the base class.
    super(argumentName);

    //Sets the argument of the current ArgumentMediator.
    this.argument = argument;
  }

  /**
   * @param argument
   * @param <A2>     is the type of the given argument.
   * @return a new {@link ObjectMediator} for the given argument.
   */
  public static <A2> ObjectMediator<A2> forArgument(final A2 argument) {
    return new ObjectMediator<>(argument);
  }

  /**
   * @param condition
   * @throws RuntimeException if the given condition is null.
   * @throws RuntimeException if the argument of the current
   *                          {@link ObjectMediator} does not fulfill the given
   *                          condition.
   */
  public final void fulfills(Predicate<A> condition) {
    //Asserts that the given condition is not null.
    if (condition == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.CONDITION);
    }

    //Asserts that the argument of the current ArgumentMediator fulfills the given
    //condition.
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
   * @throws RuntimeException if the given object is null.
   * @throws RuntimeException if the argument of the current
   *                          {@link ObjectMediator} does not have the same
   *                          {@link String} representation as the given object.
   */
  public final void hasSameStringRepresentationAs(final Object object) {
    //Asserts that the given object is not null.
    if (object == null) {
      throw ArgumentIsNullException.forArgumentType(Object.class);
    }

    //Gets the String representation of the given object.
    final var stringRepresentation = object.toString();

    //Calls other method.
    hasStringRepresentation(stringRepresentation);
  }

  /**
   * @param stringRepresentation
   * @throws RuntimeException if the argument of the current
   *                          {@link ObjectMediator} does not have the given
   *                          stringRepresentation.
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
   *                          {@link ObjectMediator} does not equal the given
   *                          object.
   */
  public final void isEqualTo(final Object object) {
    //Asserts that the argument of the current ArgumentMediator equals the given
    //object.
    if (!Objects.equals(getStoredArgument(), object)) {
      throw UnequalArgumentException.forArgumentAndValue(argument, object);
    }
  }

  /**
   * @param object
   * @throws RuntimeException if the argument of the current
   *                          {@link ObjectMediator} is not (!) the given
   *                          object.
   */
  public final void is(final Object object) {
    //Asserts that the argument of the current ArgumentMediator is the given
    //object.
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
   *                          {@link ObjectMediator} is the given object.
   */
  public final void isNot(final Object object) {
    //Asserts that the argument of the current ArgumentMediator is not the given
    //object.
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
   *                          {@link ObjectMediator} equals the given object.
   */
  public final void isNotEqualTo(final A object) {
    //Asserts that the argument of the current ArgumentMediator does not equal the
    //given object.
    if (Objects.equals(getStoredArgument(), object)) {
      throw EqualArgumentException.forArgumentAndEqualValue(argument, object);
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link ObjectMediator} is null.
   */
  public final void isNotNull() {
    //Asserts that the argument of the current ArgumentMediator is not null.
    if (argument == null) {
      throw ArgumentIsNullException.forArgumentName(getArgumentName());
    }
  }

  /**
   * @throws ArgumentIsNotNullException if the argument of the current
   *                                    {@link ObjectMediator} is not (!) null.
   */
  public final void isNull() {
    //Asserts that the argument of the current ArgumentMediator is (!) null.
    if (argument != null) {
      throw ArgumentIsNotNullException.forArgument(getStoredArgument());
    }
  }

  /**
   * @param type
   * @throws RuntimeException if the argument of the current
   *                          {@link ObjectMediator} is null.
   * @throws RuntimeException if the argument of the current
   *                          {@link ObjectMediator} is not of the given type.
   */
  public final void isOfType(final Class<?> type) {
    //Asserts that the argument of the current ArgumentMediator is not null.
    isNotNull();

    //Asserts that the argument of the current ArgumentMediator is of the given
    //type.
    if (!type.isAssignableFrom(getStoredArgument().getClass())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not a " + type);
    }
  }

  /**
   * @return the argument of the current {@link ObjectMediator}.
   */
  protected A getStoredArgument() {
    return argument;
  }
}
