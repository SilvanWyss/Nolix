//package decalration
package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.programatom.unsignedbyte.UnsignedByte;

//class
public class ByteMediator extends Mediator {

  //attribute
  private final byte argument;

  //constructor
  public ByteMediator(final byte value) {
    this.argument = value;
  }

  //constructor
  public ByteMediator(final String argumentName, final byte value) {

    super(argumentName);

    this.argument = value;
  }

  //method
  public void consistsOfBits(final String expectedBits) {

    final var actualBits = new UnsignedByte(argument).toBitString();

    if (!actualBits.equals(expectedBits)) {
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(
        getArgumentName(),
        new UnsignedByte(argument).toBitString(),
        expectedBits);
    }
  }

  //method
  public void isEqualTo(final byte value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), argument, value);
    }
  }

  //method
  public void isEqualTo(final int value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), argument, value);
    }
  }
}
