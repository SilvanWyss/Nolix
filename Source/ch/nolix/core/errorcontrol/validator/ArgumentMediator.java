package ch.nolix.core.errorcontrol.validator;

import java.util.Objects;
import java.util.function.Predicate;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNotNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link ArgumentMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 * @param <A> is the type of the argument of an {@link ArgumentMediator}.
 */
public class ArgumentMediator<A> extends Mediator {

  private final A argument;

  /**
   * Creates a new {@link ArgumentMediator} for the given argument.
   * 
   * @param argument
   */
  protected ArgumentMediator(final A argument) {

    //Calls other constructor.
    this(DEFAULT_ARGUMENT_NAME, argument);
  }

  /**
   * Creates a new {@link ArgumentMediator} for the given argument, which has the
   * given argumentName.
   * 
   * @param argumentName
   * @param argument
   * @throws ArgumentIsNullException  if the given argumentName is null.
   * @throws InvalidArgumentException if the given argument name is blank.
   */
  protected ArgumentMediator(final String argumentName, final A argument) {

    //Calls constructor of the base class.
    super(argumentName);

    //Sets the argument of the current ArgumentMediator.
    this.argument = argument;
  }

  /**
   * @param argument
   * @param <A2>     is the type of the given argument.
   * @return a new {@link ArgumentMediator} for the given argument.
   */
  public static <A2> ArgumentMediator<A2> forArgument(final A2 argument) {
    return new ArgumentMediator<>(argument);
  }

  /**
   * @param condition
   * @throws ArgumentIsNullException  if the given condition is null.
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link ArgumentMediator} does not fulfill
   *                                  the given condition.
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
   * @throws ArgumentIsNullException  if the given object is null.
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link ArgumentMediator} does not have the
   *                                  same {@link String} representation as the
   *                                  given object.
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
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link ArgumentMediator} does not have the
   *                                  given stringRepresentation.
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
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link ArgumentMediator} does not equal the
   *                                  given object.
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
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link ArgumentMediator} is not (!) the
   *                                  given object.
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
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link ArgumentMediator} is the given
   *                                  object.
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
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link ArgumentMediator} equals the given
   *                                  object.
   */
  public final void isNotEqualTo(final A object) {

    //Asserts that the argument of the current ArgumentMediator does not equal the
    //given object.
    if (Objects.equals(getStoredArgument(), object)) {
      throw EqualArgumentException.forArgumentAndEqualValue(argument, object);
    }
  }

  /**
   * @throws ArgumentIsNullException if the argument of the current
   *                                 {@link ArgumentMediator} is null.
   */
  public final void isNotNull() {

    //Asserts that the argument of the current ArgumentMediator is not null.
    if (argument == null) {
      throw ArgumentIsNullException.forArgumentName(getArgumentName());
    }
  }

  /**
   * @throws ArgumentIsNotNullException if the argument of the current
   *                                    {@link ArgumentMediator} is not (!) null.
   */
  public final void isNull() {

    //Asserts that the argument of the current ArgumentMediator is (!) null.
    if (argument != null) {
      throw ArgumentIsNotNullException.forArgument(getStoredArgument());
    }
  }

  /**
   * @param type
   * @throws ArgumentIsNullException  if the argument of the current
   *                                  {@link ArgumentMediator} is null.
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link ArgumentMediator} is not of the given
   *                                  type.
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
   * @return the argument of the current {@link ArgumentMediator}.
   */
  protected A getStoredArgument() {
    return argument;
  }
}
