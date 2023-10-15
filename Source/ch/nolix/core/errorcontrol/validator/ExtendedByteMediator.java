//package declaration
package ch.nolix.core.errorcontrol.validator;

//class
public final class ExtendedByteMediator extends ByteMediator {

  // attribute
  private final byte argument;

  // constructor
  ExtendedByteMediator(byte argument) {

    super(argument);

    this.argument = argument;
  }

  // method
  public ByteMediator thatIsNamed(final String argumentName) {
    return new ByteMediator(argumentName, argument);
  }
}
