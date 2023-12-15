//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
/**
 * A {@link StringMediator} is a {@link Mediator} for an argument that is a
 * {@link String}. A {@link StringMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public class StringMediator extends ArgumentMediator<String> {

  //constructor
  /**
   * Creates a new {@link StringMediator} for the given argument.
   * 
   * @param argument
   */
  public StringMediator(final String argument) {

    //Calls constructor of the base class.
    super(argument);
  }

  //constructor
  /**
   * Creates a new {@link StringMediator} for the given argument with the given
   * argument name.
   * 
   * @param argumentName
   * @param argument
   * @throws ArgumentIsNullException  if the given argument name is null.
   * @throws InvalidArgumentException if the given argument name is blank.
   */
  StringMediator(final String argumentName, final String argument) {

    //Calls constructor of the base class.
    super(argumentName, argument);
  }

  //method
  /**
   * @param length
   * @throws NegativeArgumentException if the given length is negative.
   * @throws ArgumentIsNullException   if the argument of the current
   *                                   {@link StringMediator} is null.
   * @throws InvalidArgumentException  if the argument of the current
   *                                   {@link StringMediator} does not have the
   *                                   given length.
   */
  public void hasLength(final int length) {

    //Asserts that the given length is not negative.
    if (length < 0) {
      throw NegativeArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.LENGTH, length);
    }

    //Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    //Asserts that the argument of the current StringMediator does not have the
    //given length.
    if (getStoredArgument().length() != length) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "does not have the length " + length);
    }
  }

  //method
  /**
   * @throws ArgumentIsNullException   if the argument of the current
   *                                   {@link StringMediator} is null.
   * @throws NonEmptyArgumentException if the argument of the current
   *                                   {@link StringMediator} is not empty.
   */
  public void isEmpty() {

    //Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    //Asserts that the argument of the current StringMediator is not empty.
    if (!getStoredArgument().isEmpty()) {
      throw NonEmptyArgumentException.forArgumentNameAndArgument(getArgumentName(), getStoredArgument());
    }
  }

  //method
  /**
   * @throws ArgumentIsNullException if the argument of the current
   *                                 {@link StringMediator} is null.
   * @throws EmptyArgumentException  if the argument of the current
   *                                 {@link StringMediator} is empty.
   */
  public void isNotEmpty() {

    //Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    //Asserts that the argument of the current StringMediator is not empty.
    if (getStoredArgument().isEmpty()) {
      throw EmptyArgumentException.forArgumentNameAndArgument(getArgumentName(), getStoredArgument());
    }
  }

  //method
  /**
   * @throws ArgumentIsNullException  if the argument of the current
   *                                  {@link StringMediator} is null.
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link StringMediator} is blank.
   */
  public void isNotBlank() {

    //Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    //Asserts that the the argument of the current StringMediator is not blank.
    if (getStoredArgument().isBlank()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is blank");
    }
  }

  //method
  /**
   * @param maxLength
   * @throws ArgumentIsNullException  if the argument of the current
   *                                  {@link StringMediator} is null.
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link StringMediator} is longer than the
   *                                  given max length says.
   */
  public void isNotLongerThan(final int maxLength) {

    //Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    //Asserts that the argument of the current StringMediator is not longer than
    //the given max length says.
    if (getStoredArgument().length() > maxLength) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is longer than " + maxLength);
    }
  }

  //method
  /**
   * @param minLength
   * @throws ArgumentIsNullException  if the argument of the current
   *                                  {@link StringMediator} is null.
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link StringMediator} is shorter than the
   *                                  given min length says.
   */
  public void isNotShorterThan(final int minLength) {

    //Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    //Asserts that the argument of the current StringMediator is not shorter than
    //the given min length says.
    if (getStoredArgument().length() < minLength) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "has the length " + getStoredArgument().length() + " and is therefore shorter than " + minLength);
    }
  }

  /**
   * @param regularExpression
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link StringMediator} does not match the
   *                                  given regularExpression.
   */
  public void matches(final String regularExpression) {

    //Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    //Asserts that the argument of the current StringMediator matches the given
    //regularExpression.
    if (!getStoredArgument().matches(regularExpression)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(getArgumentName(), getStoredArgument());
    }
  }

  //method
  /**
   * @param directory
   * @throws ArgumentIsNullException  if the argument of the current
   *                                  {@link StringMediator} is null.
   * @throws InvalidArgumentException if the given directory does not exist on the
   *                                  local machine or cannot be created on the
   *                                  local machine.
   */
  public void specifiesProbableDirectoryOnLocalMachine(final String directory) {

    //Asserts that the argument of the current StringMediator is not null.
    isNotNull();

    var specifiesProbableDirectoryOnLocalMachine = true;

    final var path = Path.of(directory);
    if (!Files.exists(path)) {
      try {
        Files.createFile(path);
        Files.delete(path);
        Files.createDirectory(path);
        Files.delete(path);
      } catch (final IOException pIOExceptione) {
        specifiesProbableDirectoryOnLocalMachine = false;
      }
    }

    if (!specifiesProbableDirectoryOnLocalMachine) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        directory,
        "is not a probable directory on the local machine");
    }
  }

  //method
  /**
   * @param sequence
   * @throws ArgumentIsNullException  if the given sequence is null.
   * @throws ArgumentIsNullException  if the argument of the current
   *                                  {@link StringMediator} is null.
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link StringMediator} does not starts with
   *                                  the givne sequence.
   */
  public void startsWith(final String sequence) {

    //Asserts that the given sequence is not null.
    if (sequence == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.SEQUENCE);
    }

    isNotNull();

    if (!getStoredArgument().startsWith(sequence)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "does not start with the sequence '" + sequence + "'");
    }
  }
}
