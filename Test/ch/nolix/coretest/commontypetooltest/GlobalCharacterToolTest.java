//package declaration
package ch.nolix.coretest.commontypetooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.GlobalCharacterTool;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GlobalCharacterToolTest extends StandardTest {

  //method
  @Test
  void testCase_isDigit_whenGivenCharacterIsADigit() {
    expect(GlobalCharacterTool.isDigit('0'));
    expect(GlobalCharacterTool.isDigit('1'));
    expect(GlobalCharacterTool.isDigit('2'));
    expect(GlobalCharacterTool.isDigit('3'));
    expect(GlobalCharacterTool.isDigit('4'));
    expect(GlobalCharacterTool.isDigit('5'));
    expect(GlobalCharacterTool.isDigit('6'));
    expect(GlobalCharacterTool.isDigit('7'));
    expect(GlobalCharacterTool.isDigit('8'));
    expect(GlobalCharacterTool.isDigit('9'));
  }

  //method
  @Test
  void testCase_isDigit_whenGivenCharacterIsNotADigit() {
    expectNot(GlobalCharacterTool.isDigit('A'));
    expectNot(GlobalCharacterTool.isDigit('a'));
    expectNot(GlobalCharacterTool.isDigit('Z'));
    expectNot(GlobalCharacterTool.isDigit('z'));
    expectNot(GlobalCharacterTool.isDigit('%'));
    expectNot(GlobalCharacterTool.isDigit('&'));
    expectNot(GlobalCharacterTool.isDigit('.'));
    expectNot(GlobalCharacterTool.isDigit(','));
    expectNot(GlobalCharacterTool.isDigit('('));
    expectNot(GlobalCharacterTool.isDigit(')'));
  }

  //method
  @Test
  void testCase_isHexadecimalDigit_whenGivenCharacterIsAHexadecimalDigit() {
    expect(GlobalCharacterTool.isHexadecimalDigit('0'));
    expect(GlobalCharacterTool.isHexadecimalDigit('1'));
    expect(GlobalCharacterTool.isHexadecimalDigit('2'));
    expect(GlobalCharacterTool.isHexadecimalDigit('3'));
    expect(GlobalCharacterTool.isHexadecimalDigit('4'));
    expect(GlobalCharacterTool.isHexadecimalDigit('5'));
    expect(GlobalCharacterTool.isHexadecimalDigit('6'));
    expect(GlobalCharacterTool.isHexadecimalDigit('7'));
    expect(GlobalCharacterTool.isHexadecimalDigit('8'));
    expect(GlobalCharacterTool.isHexadecimalDigit('9'));
    expect(GlobalCharacterTool.isHexadecimalDigit('A'));
    expect(GlobalCharacterTool.isHexadecimalDigit('B'));
    expect(GlobalCharacterTool.isHexadecimalDigit('C'));
    expect(GlobalCharacterTool.isHexadecimalDigit('D'));
    expect(GlobalCharacterTool.isHexadecimalDigit('E'));
    expect(GlobalCharacterTool.isHexadecimalDigit('F'));
  }

  //method
  @Test
  void testCase_isHexadecimalDigit_whenGivenCharacterIsNotAHexadecimalDigit() {
    expectNot(GlobalCharacterTool.isHexadecimalDigit('G'));
    expectNot(GlobalCharacterTool.isHexadecimalDigit('g'));
    expectNot(GlobalCharacterTool.isHexadecimalDigit('Z'));
    expectNot(GlobalCharacterTool.isHexadecimalDigit('z'));
    expectNot(GlobalCharacterTool.isHexadecimalDigit('%'));
    expectNot(GlobalCharacterTool.isHexadecimalDigit('&'));
    expectNot(GlobalCharacterTool.isHexadecimalDigit('.'));
    expectNot(GlobalCharacterTool.isHexadecimalDigit(','));
    expectNot(GlobalCharacterTool.isHexadecimalDigit('('));
    expectNot(GlobalCharacterTool.isHexadecimalDigit(')'));
  }
}
