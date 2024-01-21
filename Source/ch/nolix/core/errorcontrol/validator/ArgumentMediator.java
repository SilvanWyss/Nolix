//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.util.Objects;
import java.util.function.Predicate;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNotNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * A {@link ArgumentMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @param <A> is the type of the argument of an {@link ArgumentMediator}.
 */
public class ArgumentMediator<A> extends Mediator {

  //attribute
  private final A argument;

  //constructor
  /**
   * Creates a new {@link ArgumentMediator} for the given argument.
   * 
   * @param argument
   */
  public ArgumentMediator(final A argument) {

    //Calls other constructor.
    this(DEFAULT_ARGUMENT_NAME, argument);
  }

  //constructor
  /**
   * Creates a new {@link ArgumentMediator} for the given argument, that has the
   * given argument name.
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

  //method
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
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.CONDITION);
    }

    //Asserts that the argument of the current ArgumentMediator fulfills the given
    //condition.
    if (!condition.test(getStoredArgument())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "does not fulfil the given condition");
    }
  }

  //method
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

  //method
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
      throw InvalidArgumentException
        .forArgumentNameAndArgumentAndErrorPredicate(
          getArgumentName(),
          getStoredArgument(),
          "does not have the String representation '" + stringRepresentation + "'");
    }
  }

  //method
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

  //method
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
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not the given object");
    }
  }

  //method
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
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is the given object");
    }
  }

  //method
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

  //method
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

  //method
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

  //method
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
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not a " + type);
    }
  }

  //method
  /**
   * @return the argument of the current {@link ArgumentMediator}.
   */
  protected A getStoredArgument() {
    return argument;
  }
}
