//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public final class BooleanMediator extends Mediator {

  //attribute
  private final boolean argument;

  //constructor
  private BooleanMediator(final boolean argument) {
    this.argument = argument;
  }

  //constructor
  private BooleanMediator(final String argumentName, final boolean argument) {

    super(argumentName);

    this.argument = argument;
  }

  //static method
  public static BooleanMediator forArgument(final boolean argument) {
    return new BooleanMediator(argument);
  }

  //static method
  public static BooleanMediator forArgumentNameAndArgument(final String argumentName, final boolean argument) {
    return new BooleanMediator(argumentName, argument);
  }

  //method
  public void isFalse() {
    if (argument) {
      throw //
      InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(getArgumentName(), argument, "is true");
    }
  }

  //method
  public void isTrue() {
    if (!argument) {
      throw //
      InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(getArgumentName(), argument, "is false");
    }
  }
}
