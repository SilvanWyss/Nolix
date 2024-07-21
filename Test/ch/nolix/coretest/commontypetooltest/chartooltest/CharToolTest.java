//package declaration
package ch.nolix.coretest.commontypetooltest.chartooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.chartool.CharTool;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class CharToolTest extends StandardTest {

  //method
  @Test
  void testCase_isDigit_whenGivenCharacterIsADigit() {

    final var testUnit = new CharTool();

    expect(testUnit.isDigit('0'));
    expect(testUnit.isDigit('1'));
    expect(testUnit.isDigit('2'));
    expect(testUnit.isDigit('3'));
    expect(testUnit.isDigit('4'));
    expect(testUnit.isDigit('5'));
    expect(testUnit.isDigit('6'));
    expect(testUnit.isDigit('7'));
    expect(testUnit.isDigit('8'));
    expect(testUnit.isDigit('9'));
  }

  //method
  @Test
  void testCase_isDigit_whenGivenCharacterIsNotADigit() {

    final var testUnit = new CharTool();

    expectNot(testUnit.isDigit('A'));
    expectNot(testUnit.isDigit('a'));
    expectNot(testUnit.isDigit('Z'));
    expectNot(testUnit.isDigit('z'));
    expectNot(testUnit.isDigit('%'));
    expectNot(testUnit.isDigit('&'));
    expectNot(testUnit.isDigit('.'));
    expectNot(testUnit.isDigit(','));
    expectNot(testUnit.isDigit('('));
    expectNot(testUnit.isDigit(')'));
  }

  //method
  @Test
  void testCase_isHexadecimalDigit_whenGivenCharacterIsAHexadecimalDigit() {

    final var testUnit = new CharTool();

    expect(testUnit.isHexadecimalDigit('0'));
    expect(testUnit.isHexadecimalDigit('1'));
    expect(testUnit.isHexadecimalDigit('2'));
    expect(testUnit.isHexadecimalDigit('3'));
    expect(testUnit.isHexadecimalDigit('4'));
    expect(testUnit.isHexadecimalDigit('5'));
    expect(testUnit.isHexadecimalDigit('6'));
    expect(testUnit.isHexadecimalDigit('7'));
    expect(testUnit.isHexadecimalDigit('8'));
    expect(testUnit.isHexadecimalDigit('9'));
    expect(testUnit.isHexadecimalDigit('A'));
    expect(testUnit.isHexadecimalDigit('B'));
    expect(testUnit.isHexadecimalDigit('C'));
    expect(testUnit.isHexadecimalDigit('D'));
    expect(testUnit.isHexadecimalDigit('E'));
    expect(testUnit.isHexadecimalDigit('F'));
  }

  //method
  @Test
  void testCase_isHexadecimalDigit_whenGivenCharacterIsNotAHexadecimalDigit() {

    final var testUnit = new CharTool();

    expectNot(testUnit.isHexadecimalDigit('G'));
    expectNot(testUnit.isHexadecimalDigit('g'));
    expectNot(testUnit.isHexadecimalDigit('Z'));
    expectNot(testUnit.isHexadecimalDigit('z'));
    expectNot(testUnit.isHexadecimalDigit('%'));
    expectNot(testUnit.isHexadecimalDigit('&'));
    expectNot(testUnit.isHexadecimalDigit('.'));
    expectNot(testUnit.isHexadecimalDigit(','));
    expectNot(testUnit.isHexadecimalDigit('('));
    expectNot(testUnit.isHexadecimalDigit(')'));
  }
}
