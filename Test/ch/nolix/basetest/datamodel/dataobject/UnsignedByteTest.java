/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datamodel.dataobject;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datamodel.dataobject.UnsignedByte;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class UnsignedByteTest extends StandardTest {
  @Test
  void testCase_getBitAtAsInt_1A() {
    // setup
    final var testUnit = UnsignedByte.withBits(0, 0, 0, 0, 0, 0, 0, 0);

    // execute
    final var resultBit1 = testUnit.getBitAtAsInt(1);
    final var resultBit2 = testUnit.getBitAtAsInt(2);
    final var resultBit3 = testUnit.getBitAtAsInt(3);
    final var resultBit4 = testUnit.getBitAtAsInt(4);
    final var resultBit5 = testUnit.getBitAtAsInt(5);
    final var resultBit6 = testUnit.getBitAtAsInt(6);
    final var resultBit7 = testUnit.getBitAtAsInt(7);
    final var resultBit8 = testUnit.getBitAtAsInt(8);

    // verify
    expect(resultBit1).isEqualTo(0);
    expect(resultBit2).isEqualTo(0);
    expect(resultBit3).isEqualTo(0);
    expect(resultBit4).isEqualTo(0);
    expect(resultBit5).isEqualTo(0);
    expect(resultBit6).isEqualTo(0);
    expect(resultBit7).isEqualTo(0);
    expect(resultBit8).isEqualTo(0);
  }

  @Test
  void testCase_getBitAtAsInt_1B() {
    // setup
    final var testUnit = UnsignedByte.withBits(0, 0, 0, 0, 0, 1, 1, 1);

    // execute
    final var resultBit1 = testUnit.getBitAtAsInt(1);
    final var resultBit2 = testUnit.getBitAtAsInt(2);
    final var resultBit3 = testUnit.getBitAtAsInt(3);
    final var resultBit4 = testUnit.getBitAtAsInt(4);
    final var resultBit5 = testUnit.getBitAtAsInt(5);
    final var resultBit6 = testUnit.getBitAtAsInt(6);
    final var resultBit7 = testUnit.getBitAtAsInt(7);
    final var resultBit8 = testUnit.getBitAtAsInt(8);

    // verify
    expect(resultBit1).isEqualTo(0);
    expect(resultBit2).isEqualTo(0);
    expect(resultBit3).isEqualTo(0);
    expect(resultBit4).isEqualTo(0);
    expect(resultBit5).isEqualTo(0);
    expect(resultBit6).isEqualTo(1);
    expect(resultBit7).isEqualTo(1);
    expect(resultBit8).isEqualTo(1);
  }

  @Test
  void testCase_getBitAtAsInt_1C() {
    // setup
    final var testUnit = UnsignedByte.withBits(1, 1, 1, 0, 0, 0, 0, 0);

    // execute
    final var resultBit1 = testUnit.getBitAtAsInt(1);
    final var resultBit2 = testUnit.getBitAtAsInt(2);
    final var resultBit3 = testUnit.getBitAtAsInt(3);
    final var resultBit4 = testUnit.getBitAtAsInt(4);
    final var resultBit5 = testUnit.getBitAtAsInt(5);
    final var resultBit6 = testUnit.getBitAtAsInt(6);
    final var resultBit7 = testUnit.getBitAtAsInt(7);
    final var resultBit8 = testUnit.getBitAtAsInt(8);

    // verify
    expect(resultBit1).isEqualTo(1);
    expect(resultBit2).isEqualTo(1);
    expect(resultBit3).isEqualTo(1);
    expect(resultBit4).isEqualTo(0);
    expect(resultBit5).isEqualTo(0);
    expect(resultBit6).isEqualTo(0);
    expect(resultBit7).isEqualTo(0);
    expect(resultBit8).isEqualTo(0);
  }

  @Test
  void testCase_getBitAtAsInt_1D() {
    // setup
    final var testUnit = UnsignedByte.withBits(1, 1, 1, 1, 1, 1, 1, 1);

    // execute
    final var resultBit1 = testUnit.getBitAtAsInt(1);
    final var resultBit2 = testUnit.getBitAtAsInt(2);
    final var resultBit3 = testUnit.getBitAtAsInt(3);
    final var resultBit4 = testUnit.getBitAtAsInt(4);
    final var resultBit5 = testUnit.getBitAtAsInt(5);
    final var resultBit6 = testUnit.getBitAtAsInt(6);
    final var resultBit7 = testUnit.getBitAtAsInt(7);
    final var resultBit8 = testUnit.getBitAtAsInt(8);

    // verify
    expect(resultBit1).isEqualTo(1);
    expect(resultBit2).isEqualTo(1);
    expect(resultBit3).isEqualTo(1);
    expect(resultBit4).isEqualTo(1);
    expect(resultBit5).isEqualTo(1);
    expect(resultBit6).isEqualTo(1);
    expect(resultBit7).isEqualTo(1);
    expect(resultBit8).isEqualTo(1);
  }

  @Test
  void testCase_toString_1A() {
    // setup
    final var testUnit = UnsignedByte.withBits(0, 0, 0, 0, 0, 0, 0, 0);

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("00000000");
  }

  @Test
  void testCase_toString_1B() {
    // setup
    final var testUnit = UnsignedByte.withBits(0, 0, 0, 0, 0, 1, 1, 1);

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("00000111");
  }

  @Test
  void testCase_toString_1C() {
    // setup
    final var testUnit = UnsignedByte.withBits(1, 1, 1, 0, 0, 0, 0, 0);

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("11100000");
  }

  @Test
  void testCase_toString_1D() {
    // setup
    final var testUnit = UnsignedByte.withBits(1, 1, 1, 1, 1, 1, 1, 1);

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("11111111");
  }

  @Test
  void testCase_toByte() {
    for (var i = 0; i <= 255; i++) {
      // setup
      final var testUnit = UnsignedByte.fromNumber(i);

      // execute
      final var resullt = testUnit.toByte();

      // verify
      expect(resullt).isEqualTo(i - 128);
    }
  }

  @Test
  void testCase_toInt_1A() {
    // setup
    final var testUnit = UnsignedByte.withBits(0, 0, 0, 0, 0, 0, 0, 0);

    // execute
    final var result = testUnit.toInt();

    // verify
    expect(result).isEqualTo(0);
  }

  @Test
  void testCase_toInt_1B() {
    // setup
    final var testUnit = UnsignedByte.withBits(0, 0, 0, 0, 0, 1, 1, 1);

    // execute
    final var result = testUnit.toInt();

    // verify
    expect(result).isEqualTo(7);
  }

  @Test
  void testCase_toInt_1C() {
    // setup
    final var testUnit = UnsignedByte.withBits(1, 1, 1, 0, 0, 0, 0, 0);

    // execute
    final var result = testUnit.toInt();

    // verify
    expect(result).isEqualTo(224);
  }

  @Test
  void testCase_toInt_1D() {
    // setup
    final var testUnit = UnsignedByte.withBits(1, 1, 1, 1, 1, 1, 1, 1);

    // execute
    final var result = testUnit.toInt();

    // verify
    expect(result).isEqualTo(255);
  }
}
