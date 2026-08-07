/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datamodel.fieldvalue;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datamodel.fieldvalue.ValueMapper;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;

/**
 * @author Silvan Wyss
 */
final class ValueMapperTest extends StandardTest {
  @Test
  void testCase_mapStringToValue_whenGivenStringRepresentsAByte() {
    // setup
    final var testUnit = new ValueMapper();

    // execute
    final var result = testUnit.mapStringToValue("25", DataType.INTEGER_1BYTE);

    // verify
    final byte expectedResult = 25;
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_mapStringToValue_whenGivenStringRepresentsAShort() {
    // setup
    final var testUnit = new ValueMapper();

    // execute
    final var result = testUnit.mapStringToValue("25000", DataType.INTEGER_2BYTE);

    // verify
    final short expectedResult = 25_000;
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_mapStringToValue_whenGivenStringRepresentsAnInt() {
    // setup
    final var testUnit = new ValueMapper();

    // execute
    final var result = testUnit.mapStringToValue("25000000", DataType.INTEGER_4BYTE);

    // verify
    final int expectedResult = 25_000_000;
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_mapStringToValue_whenGivenStringRepresentsALong() {
    // setup
    final var testUnit = new ValueMapper();

    // execute
    final var result = testUnit.mapStringToValue("25000000000", DataType.INTEGER_8BYTE);

    // verify
    final long expectedResult = 25_000_000_000L;
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_mapStringToValue_whenGivenStringRepresentsAFloat() {
    // setup
    final var testUnit = new ValueMapper();

    // execute
    final var result = testUnit.mapStringToValue("3.141592", DataType.FLOATING_POINT_NUMBER_4BYTE);

    // verify
    final float expectedResult = 3.141592F;
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_mapStringToValue_whenGivenStringRepresentsADouble() {
    // setup
    final var testUnit = new ValueMapper();

    // execute
    final var result = testUnit.mapStringToValue("3.141592653589", DataType.FLOATING_POINT_NUMBER_8BYTE);

    // verify
    final double expectedResult = 3.141592653589;
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_mapStringToValue_whenGivenStringRepresentsABoolean() {
    // setup
    final var testUnit = new ValueMapper();

    // execute
    final var result = testUnit.mapStringToValue("true", DataType.BOOLEAN);

    // verify
    final Boolean expectedResult = Boolean.TRUE;
    expect(result).is(expectedResult);
  }

  @Test
  void testCase_mapStringToValue_whenGivenStringRepresentsASring() {
    // setup
    final var testUnit = new ValueMapper();

    // execute
    final var result = testUnit.mapStringToValue("Lorem ipsum", DataType.STRING);

    // verify
    final String expectedResult = "Lorem ipsum";
    expect(result).isEqualTo(expectedResult);
  }
}
