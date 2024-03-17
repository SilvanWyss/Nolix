//package declaration
package ch.nolix.systemtest.graphictest.colortest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.graphic.color.Color;

//class
public final class ColorTest extends StandardTest {

  //method
  @Test
  public void testCase_equals_whenGivenColorIsEqual() {

    //setup
    final var testUnit = Color.fromString("0x102030");
    final var color = Color.fromString("0x102030");

    //execution
    final var result = testUnit.equals(color);

    //verification
    expect(result);
  }

  //method
  @Test
  public void testCase_equals_whenGivenColorIsNotEqual() {

    //setup
    final var testUnit = Color.fromString("0x102030");
    final var color = Color.fromString("0x101010");

    //execution
    final var result = testUnit.equals(color);

    //verification
    expectNot(result);
  }

  //method
  @Test
  public void testCase_fromString_1A() {

    //execution
    final var result = Color.fromString("0x000000");

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x000000");
  }

  //method
  @Test
  public void testCase_fromString_1B() {

    //execution
    final var result = Color.fromString("0xFFFFFF");

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0xFFFFFF");
  }

  //method
  @Test
  public void testCase_getAlphaValue() {

    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.getAlphaValue();

    //verification
    expect(result).isEqualTo(0xA0);
  }

  //method
  @Test
  public void testCase_getBlueValue() {

    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.getBlueValue();

    //verification
    expect(result).isEqualTo(0x30);
  }

  //method
  @Test
  public void testCase_getGreenValue() {

    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.getGreenValue();

    //verification
    expect(result).isEqualTo(0x20);
  }

  //method
  @Test
  public void testCase_getColorNameOrHexadecimalString_1A() {

    //setup
    final var testUnit = Color.ALICE_BLUE;

    //execution
    final var result = testUnit.getColorNameOrHexadecimalString();

    //verification
    expect(result).isEqualTo("AliceBlue");
  }

  //method
  @Test
  public void testCase_getColorNameOrHexadecimalString_1B() {

    //setup
    final var testUnit = Color.YELLOW_GREEN;

    //execution
    final var result = testUnit.getColorNameOrHexadecimalString();

    //verification
    expect(result).isEqualTo("YellowGreen");
  }

  //method
  @Test
  public void testCase_getInvertedColor_1A() {

    //setup
    final var testUnit = Color.fromLong(0x000000);

    //execution
    final var result = testUnit.getInvertedColor();

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0xFFFFFF");
  }

  //method
  @Test
  public void testCase_getInvertedColor_1B() {

    //setup
    final var testUnit = Color.fromLong(0xFFFFFF);

    //execution
    final var result = testUnit.getInvertedColor();

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x000000");
  }

  //method
  @Test
  public void testCase_getRedValue() {

    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.getRedValue();

    //verification
    expect(result).isEqualTo(0x10);
  }

  //method
  @Test
  public void testCase_getSpecification() {

    //setup
    final var testUnit = Color.fromString("0x102030");

    //execution
    final var result = testUnit.getSpecification();

    //verification
    expect(result).hasStringRepresentation("Color(0x102030)");
  }

  //method
  @Test
  public void testCase_toHexadecimalStringWithAlphaValue_1A() {

    //setup
    final var testUnit = Color.fromString("0x10203000");

    //execution
    final var result = testUnit.toHexadecimalStringWithAlphaValue();

    //verification
    expect(result).isEqualTo("0x10203000");
  }

  //method
  @Test
  public void testCase_toHexadecimalStringWithAlphaValue_1B() {

    //setup
    final var testUnit = Color.fromString("0x1020307F");

    //execution
    final var result = testUnit.toHexadecimalStringWithAlphaValue();

    //verification
    expect(result).isEqualTo("0x1020307F");
  }

  //method
  @Test
  public void testCase_toHexadecimalStringWithAlphaValue_1C() {

    //setup
    final var testUnit = Color.fromString("0x102030FF");

    //execution
    final var result = testUnit.toHexadecimalStringWithAlphaValue();

    //verification
    expect(result).isEqualTo("0x102030FF");
  }

  //method
  @Test
  public void testCase_withAlphaValue_1A() {

    //setup
    final var testUnit = Color.fromString("0x10203000");

    //execution
    final var result = testUnit.withAlphaValue(0);

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x10203000");
  }

  //method
  @Test
  public void testCase_withAlphaValue_1B() {

    //setup
    final var testUnit = Color.fromString("0x10203000");

    //execution
    final var result = testUnit.withAlphaValue(160);

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x102030A0");
  }

  //method
  @Test
  public void testCase_withFloatingPointAlphaValue_1A() {

    //setup
    final var testUnit = Color.fromString("0x102030");

    //execution
    final var result = testUnit.withFloatingPointAlphaValue(0.0);

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x10203000");
  }

  //method
  @Test
  public void testCase_withFloatingPointAlphaValue_1B() {

    //setup
    final var testUnit = Color.fromString("0x102030");

    //execution
    final var result = testUnit.withFloatingPointAlphaValue(0.5);

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x1020307F");
  }

  //method
  @Test
  public void testCase_withFloatingPointAlphaValue_1C() {

    //setup
    final var testUnit = Color.fromString("0x102030");

    //execution
    final var result = testUnit.withFloatingPointAlphaValue(1.0);

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x102030");
  }

  //method
  @Test
  public void testCase_withFullAlphaValue_1A() {

    //setup
    final var testUnit = Color.fromString("0x102030");

    //execution
    final var result = testUnit.withFullAlphaValue();

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x102030");
  }

  //method
  @Test
  public void testCase_withFullAlphaValue_1B() {

    //setup
    final var testUnit = Color.fromString("0x102030A0");

    //execution
    final var result = testUnit.withFullAlphaValue();

    //verification
    expect(result.toHexadecimalString()).isEqualTo("0x102030");
  }
}
