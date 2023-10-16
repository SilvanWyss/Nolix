//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
/**
 * A string container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public final class MultiStringMediator extends MultiArgumentMediator<String> {

  //constructor
  /**
   * Creates a new string container mediator with the given arguments.
   * 
   * @param arguments
   * @throws ArgumentIsNullException if the given argument container is null.
   */
  MultiStringMediator(final Iterable<String> arguments) {

    //Calls constructor of the base class.
    super(arguments);
  }

  //constructor
  /**
   * Creates a new string container mediator with the given arguments.
   * 
   * @param arguments
   * @throws ArgumentIsNullException if the given argument container is null.
   */
  MultiStringMediator(final String[] arguments) {

    //Calls method of the base class.
    super(arguments);
  }

  //method
  /**
   * for the arguments of the current {@link MultiStringMediator}.
   * 
   * @throws ArgumentIsNullException  if one of the arguments of the current
   *                                  {@link MultiStringMediator} is null.
   * @throws InvalidArgumentException if one of the arguments of the current
   *                                  {@link MultiStringMediator} is blank.
   */
  public void areNotBlank() {

    //Asserts that the arguments of the current multi string mediator are not null.
    areNotNull();

    //Iterates the arguments of the current multi string mediator.
    var index = 1;
    for (final var a : getStoredArguments()) {

      //Asserts that the current argument is not blank.
      if (a.isBlank()) {
        throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(index + "th argument", a,
            "is blank");
      }

      //Increments index.
      index++;
    }
  }

  //method
  /**
   * @throws ArgumentIsNullException if one of the arguments of this strinc
   *                                 container mediator is null.
   * @throws EmptyArgumentException  if one of the arguments of this string
   *                                 container mediator is empty.
   */
  public void areNotEmpty() {

    //Asserts that the arguments of this string container mediator are not null.
    areNotNull();

    //Iterates the arguments of this string container mediator.
    var index = 1;
    for (final String a : getStoredArguments()) {

      //Asserts that the current argument is not empty.
      if (a.isEmpty()) {
        throw EmptyArgumentException.forArgumentNameAndArgument(index + "th argument", a);
      }

      //Increments index.
      index++;
    }
  }
}
