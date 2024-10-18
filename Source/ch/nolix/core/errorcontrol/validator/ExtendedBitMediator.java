package ch.nolix.core.errorcontrol.validator;

public final class ExtendedBitMediator extends BitMediator {

  private final boolean argument;

  ExtendedBitMediator(boolean argument) {

    super(argument);

    this.argument = argument;
  }

  public BitMediator thatIsNamed(final String argumentName) {
    return new BitMediator(argumentName, argument);
  }
}
