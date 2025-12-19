package ch.nolix.core.errorcontrol.validator;

/**
 * @author Silvan Wyss
 */
public final class ExtendedByteMediator extends ByteMediator {
  private final byte argument;

  ExtendedByteMediator(byte argument) {
    super(argument);

    this.argument = argument;
  }

  public ByteMediator thatIsNamed(final String argumentName) {
    return new ByteMediator(argumentName, argument);
  }
}
