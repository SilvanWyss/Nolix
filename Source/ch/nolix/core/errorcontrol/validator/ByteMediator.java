package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.misc.dataobject.UnsignedByte;

public class ByteMediator extends Mediator {

  private final byte argument;

  public ByteMediator(final byte value) {
    this.argument = value;
  }

  public ByteMediator(final String argumentName, final byte value) {

    super(argumentName);

    this.argument = value;
  }

  public void consistsOfBits(final String expectedBits) {

    final var actualBits = new UnsignedByte(argument).toBitString();

    if (!actualBits.equals(expectedBits)) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(
        new UnsignedByte(argument).toBitString(),
        getArgumentName(),
        expectedBits);
    }
  }

  public void isEqualTo(final byte value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(argument, getArgumentName(), value);
    }
  }

  public void isEqualTo(final int value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(argument, getArgumentName(), value);
    }
  }
}
