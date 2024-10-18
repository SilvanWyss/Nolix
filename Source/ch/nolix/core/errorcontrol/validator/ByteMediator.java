package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.programatom.unsignedbyte.UnsignedByte;

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
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(
        getArgumentName(),
        new UnsignedByte(argument).toBitString(),
        expectedBits);
    }
  }

  public void isEqualTo(final byte value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), argument, value);
    }
  }

  public void isEqualTo(final int value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), argument, value);
    }
  }
}
