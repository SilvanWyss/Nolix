//package declaration
package ch.nolix.core.commontype.commontypewrapper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A {@link ByteWrapper} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2019-09-06
 */
public final class ByteWrapper {

  //static method
  /**
   * @param number
   * @return a new {@link ByteWrapper} from the given number.
   * @throws ArgumentIsOutOfRangeException if the given number is not in [0, 255].
   */
  public static ByteWrapper fromNumber(final int number) {

    //Asserts that the given number is in [0, 255].
    GlobalValidator.assertThat(number).thatIsNamed(LowerCaseCatalogue.NUMBER).isBetween(0, 255);

    return new ByteWrapper((byte) (number - 128));
  }

  //attribute
  /**
   * A {@link ByteWrapper} stores its value in an unsigned int because for any
   * transformation it would be needed to transform the value to an int anyway.
   */
  private final int mByte;

  //constructor
  /**
   * Creates a new {@link ByteWrapper} with the given pByte.
   * 
   * @param pByte
   */
  public ByteWrapper(final byte pByte) {
    mByte = pByte & 0b11111111;
  }

  //constructor
  /**
   * Creates a new {@link ByteWrapper} with the given bits.
   * 
   * @param bit1
   * @param bit2
   * @param bit3
   * @param bit4
   * @param bit5
   * @param bit6
   * @param bit7
   * @param bit8
   */
  public ByteWrapper( //NOSONAR: A byte consists of 8 bits.
      final int bit1,
      final int bit2,
      final int bit3,
      final int bit4,
      final int bit5,
      final int bit6,
      final int bit7,
      final int bit8) {
    mByte = 128 * bit1 + 64 * bit2 + 32 * bit3 + 16 * bit4 + 8 * bit5 + 4 * bit6 + 2 * bit7 + bit8;
  }

  //method
  /**
   * @param index
   * @return the bit at the given index from the current {@link ByteWrapper}.
   */
  public boolean getBitAt(final int index) {
    return (getBitAtAsInt(index) == 1);
  }

  //method
  /**
   * @param index
   * @return the bit at the given index from the current {@link ByteWrapper} as
   *         int.
   */
  public int getBitAtAsInt(final int index) {

    GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isBetween(1, 8);

    return (mByte >> (8 - index)) & 1;
  }

  //method
  /**
   * @return a {@link String} with the bits of the current {@link ByteWrapper}.
   */
  public String toBitString() {
    return String.format(
        "%d%d%d%d%d%d%d%d",
        getBitAtAsInt(1),
        getBitAtAsInt(2),
        getBitAtAsInt(3),
        getBitAtAsInt(4),
        getBitAtAsInt(5),
        getBitAtAsInt(6),
        getBitAtAsInt(7),
        getBitAtAsInt(8));
  }

  //method
  /**
   * @return the current {@link ByteWrapper} as byte.
   */
  public byte toByte() {
    return (byte) mByte;
  }

  //method
  /**
   * @return the current {@link ByteWrapper} as int.
   */
  public int toInt() {
    return mByte;
  }
}
