package ch.nolix.core.errorcontrol.validator;

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
