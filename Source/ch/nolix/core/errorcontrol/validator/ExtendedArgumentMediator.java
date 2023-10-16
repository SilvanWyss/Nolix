//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;

//class
/**
 * An extended generic argument mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-11-12
 * @param <A> is the type of the argument of an extended generic argument
 *            mediator.
 */
public class ExtendedArgumentMediator<A> extends ArgumentMediator<A> {

  //constructor
  /**
   * Creates a new extended generic argument mediator for the given argument.
   * 
   * @param argument
   */
  ExtendedArgumentMediator(final A argument) {

    //Calls constructor of the base class.
    super(argument);
  }

  //method
  /**
   * @param type
   * @return a new generic argument mediator with the argument name from the given
   *         type and for the argument of this extended generic argument mediator.
   */
  public final ArgumentMediator<A> thatIsNamed(final Class<?> type) {
    return new ArgumentMediator<>(type.getSimpleName(), getStoredArgument());
  }

  //method
  /**
   * @param argumentName
   * @return a new generic argument mediator with the given argument name and for
   *         the argument of this extended generic argument mediator.
   * @throws ArgumentIsNullException if the given argument name is null.
   * @throws EmptyArgumentException  if the given argument name is empty.
   */
  public ArgumentMediator<A> thatIsNamed(final String argumentName) {
    return new ArgumentMediator<>(argumentName, getStoredArgument());
  }
}
