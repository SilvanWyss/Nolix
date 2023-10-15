//package declaration
package ch.nolix.core.errorcontrol.validator;

//class
public final class ExtendedBitMediator extends BitMediator {

  // attribute
  private final boolean argument;

  // constructor
  ExtendedBitMediator(boolean argument) {

    super(argument);

    this.argument = argument;
  }

  // method
  public BitMediator thatIsNamed(final String argumentName) {
    return new BitMediator(argumentName, argument);
  }
}
