package ch.nolix.systemtest.graphic.color;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;

/**
 * @author Silvan Wyss
 */
final class ColorTest extends StandardTest {
  @Test
  void testCase_equals_whenGivenColorIsEqual() {
    //setup
    final var testUnit = Color.fromString("0x102030");
    final var color = Color.fromString("0x102030");

    //execution
    final var result = testUnit.equals(color);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_equals_whenGivenColorIsNotEqual() {
    //setup
    final var testUnit = Color.fromString("0x102030");
    final var color = Color.fromString("0x101010");

    //execution
    final var result = testUnit.equals(color);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_fromString_1A() {
    //execution
    final var result = Color.fromString("0x000000");

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x000000");
  }

  @Test
  void testCase_fromString_1B() {
    //execution
    final var result = Color.fromString("0xFFFFFF");

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0xFFFFFF");
  }

  @Test
  void testCase_getAlphaValue() {
    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.getAlphaValue();

    //verification
    expect(result).isEqualTo(0xA0);
  }

  @Test
  void testCase_getBlueValue() {
    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.getBlueValue();

    //verification
    expect(result).isEqualTo(0x30);
  }

  @Test
  void testCase_getGreenValue() {
    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.getGreenValue();

    //verification
    expect(result).isEqualTo(0x20);
  }

  @Test
  void testCase_getColorNameOrHexadecimalString_1A() {
    //setup
    final var testUnit = X11ColorCatalog.ALICE_BLUE;

    //execution
    final var result = testUnit.getColorNameOrHexadecimalString();

    //verification
    expect(result).isEqualTo("AliceBlue");
  }

  @Test
  void testCase_getColorNameOrHexadecimalString_1B() {
    //setup
    final var testUnit = X11ColorCatalog.YELLOW_GREEN;

    //execution
    final var result = testUnit.getColorNameOrHexadecimalString();

    //verification
    expect(result).isEqualTo("YellowGreen");
  }

  @Test
  void testCase_getInvertedColor_1A() {
    //setup
    final var testUnit = Color.fromLong(0x000000);

    //execution
    final var result = testUnit.getInvertedColor();

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0xFFFFFF");
  }

  @Test
  void testCase_getInvertedColor_1B() {
    //setup
    final var testUnit = Color.fromLong(0xFFFFFF);

    //execution
    final var result = testUnit.getInvertedColor();

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x000000");
  }

  @Test
  void testCase_getRedValue() {
    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.getRedValue();

    //verification
    expect(result).isEqualTo(0x10);
  }

  @Test
  void testCase_getSpecification() {
    //setup
    final var testUnit = Color.fromString("0x102030");

    //execution
    final var result = testUnit.getSpecification();

    //verification
    expect(result).hasStringRepresentation("Color(0x102030)");
  }

  @ParameterizedTest
  @ValueSource(strings = {
  "0x00000001",
  "0x0000000F",
  "0x00000010",
  "0x000000F0",
  "0x00000100",
  "0x00000F00",
  "0x00001000",
  "0x0000F000",
  "0x00010000",
  "0x000F0000",
  "0x00100000",
  "0x00F00000",
  "0x01000000",
  "0x0F000000",
  "0x10000000",
  "0xF0000000",
  "0x00000000",
  "0x01010101",
  "0xF0F0F0F0",
  "0xFFFFFFFF"
  })
  void testCase_toHexadecimalStringWithAlphaValu(final String string) {
    //setup
    final var testUnit = Color.fromString(string);

    //execution
    final var result = testUnit.toHexadecimalStringWithAlphaValue();

    //verification
    expect(result).isEqualTo(string);
  }

  @Test
  void testCase_withAlphaValue_1A() {
    //setup
    final var testUnit = Color.fromString("0x10203000");

    //execution
    final var result = testUnit.withAlphaValue(0);

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x10203000");
  }

  @Test
  void testCase_withAlphaValue_1B() {
    //setup
    final var testUnit = Color.fromString("0x10203000");

    //execution
    final var result = testUnit.withAlphaValue(160);

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x102030A0");
  }

  @ParameterizedTest
  @CsvSource({
  "0x010203, 0.0, 0x01020300",
  "0x010203, 0.25, 0x0102033F",
  "0x010203, 0.5, 0x0102037F",
  "0x010203, 0.75, 0x010203BF",
  "0x010203, 1.0, 0x010203",
  })
  void testCase_withFloatingPointAlphaValue(
    final String colorAsHexadecimalString,
    final double floatingPointAlphaValue,
    final String expectedHexadecimaString) {
    //setup
    final var testUnit = Color.fromString(colorAsHexadecimalString);

    //execution
    final var result = testUnit.withFloatingPointAlphaValue(floatingPointAlphaValue);

    //verification
    expect(result.toHexadecimalString()).isEqualTo(expectedHexadecimaString);
  }

  @ParameterizedTest
  @CsvSource({
  "0x01020300, 0x010203",
  "0x01020301, 0x010203",
  "0x0102030F, 0x010203",
  "0x010203F0, 0x010203",
  "0x010203F1, 0x010203",
  "0x010203FF, 0x010203",
  })
  void testCase_withFullAlphaValue(
    final String colorString,
    final String expectedResult) {
    //setup
    final var testUnit = Color.fromString(colorString);

    //execution
    final var result = testUnit.withFullAlphaValue();

    //verification
    expect(result.toHexadecimalString()).isEqualTo(expectedResult);
  }
}
