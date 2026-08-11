/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datamodel.dataobject;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link UnsignedByte} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class UnsignedByte {
  /**
   * A {@link UnsignedByte} stores its value in an unsigned int because for any
   * transformation it would be needed to transform the value to an int anyway.
   */
  private final int memberByte;

  /**
   * Creates a new {@link UnsignedByte} from the given paramByte.
   * 
   * @param paramByte
   */
  private UnsignedByte(final byte paramByte) {
    memberByte = paramByte & 0b11111111;
  }

  /**
   * Creates a new {@link UnsignedByte} with the given bits.
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
  private UnsignedByte( // NOSONAR: A byte consists of 8 bits.
    final int bit1,
    final int bit2,
    final int bit3,
    final int bit4,
    final int bit5,
    final int bit6,
    final int bit7,
    final int bit8) {
    memberByte = 128 * bit1 + 64 * bit2 + 32 * bit3 + 16 * bit4 + 8 * bit5 + 4 * bit6 + 2 * bit7 + bit8;
  }

  /**
   * @param paramByte
   * @return a new {@link UnsignedByte} from the given paramByte.
   */
  public static UnsignedByte fromByte(final byte paramByte) {
    return new UnsignedByte(paramByte);
  }

  /**
   * @param number
   * @return a new {@link UnsignedByte} from the given number
   * @throws RuntimeException if the given number is not in [0, 255]
   */
  public static UnsignedByte fromNumber(final int number) {
    // Asserts that the given number is in [0, 255].
    Validator.assertThat(number).thatIsNamed(LowerCaseVariableNameCatalog.NUMBER).isBetween(0, 255);

    return new UnsignedByte((byte) (number - 128));
  }

  /**
   * @param bit1
   * @param bit2
   * @param bit3
   * @param bit4
   * @param bit5
   * @param bit6
   * @param bit7
   * @param bit8
   * @return a new {@link UnsignedByte} with the given bits.
   */
  public static UnsignedByte withBits( // NOSONAR: A byte consists of 8 bits.
    final int bit1,
    final int bit2,
    final int bit3,
    final int bit4,
    final int bit5,
    final int bit6,
    final int bit7,
    final int bit8) {
    return new UnsignedByte(bit1, bit2, bit3, bit4, bit5, bit6, bit7, bit8);
  }

  /**
   * @param index
   * @return the bit at the given index from the current {@link UnsignedByte}
   */
  public boolean getBitAt(final int index) { // NOSONAR: This method returns a bit as a boolean.
    return (getBitAtAsInt(index) == 1);
  }

  /**
   * @param index
   * @return the bit at the given index from the current {@link UnsignedByte} as
   *         int.
   */
  public int getBitAtAsInt(final int index) {
    Validator.assertThat(index).thatIsNamed(LowerCaseVariableNameCatalog.INDEX).isBetween(1, 8);

    return (memberByte >> (8 - index)) & 1;
  }

  /**
   * @return the current {@link UnsignedByte} as byte.
   */
  public byte toByte() {
    return (byte) memberByte;
  }

  /**
   * @return the current {@link UnsignedByte} as int.
   */
  public int toInt() {
    return memberByte;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return //
    String.format(
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
}
