//package declaration
package ch.nolix.coretest.commontypetooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.chartool.GlobalCharTool;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GlobalCharacterToolTest extends StandardTest {

  //method
  @Test
  void testCase_isDigit_whenGivenCharacterIsADigit() {
    expect(GlobalCharTool.isDigit('0'));
    expect(GlobalCharTool.isDigit('1'));
    expect(GlobalCharTool.isDigit('2'));
    expect(GlobalCharTool.isDigit('3'));
    expect(GlobalCharTool.isDigit('4'));
    expect(GlobalCharTool.isDigit('5'));
    expect(GlobalCharTool.isDigit('6'));
    expect(GlobalCharTool.isDigit('7'));
    expect(GlobalCharTool.isDigit('8'));
    expect(GlobalCharTool.isDigit('9'));
  }

  //method
  @Test
  void testCase_isDigit_whenGivenCharacterIsNotADigit() {
    expectNot(GlobalCharTool.isDigit('A'));
    expectNot(GlobalCharTool.isDigit('a'));
    expectNot(GlobalCharTool.isDigit('Z'));
    expectNot(GlobalCharTool.isDigit('z'));
    expectNot(GlobalCharTool.isDigit('%'));
    expectNot(GlobalCharTool.isDigit('&'));
    expectNot(GlobalCharTool.isDigit('.'));
    expectNot(GlobalCharTool.isDigit(','));
    expectNot(GlobalCharTool.isDigit('('));
    expectNot(GlobalCharTool.isDigit(')'));
  }

  //method
  @Test
  void testCase_isHexadecimalDigit_whenGivenCharacterIsAHexadecimalDigit() {
    expect(GlobalCharTool.isHexadecimalDigit('0'));
    expect(GlobalCharTool.isHexadecimalDigit('1'));
    expect(GlobalCharTool.isHexadecimalDigit('2'));
    expect(GlobalCharTool.isHexadecimalDigit('3'));
    expect(GlobalCharTool.isHexadecimalDigit('4'));
    expect(GlobalCharTool.isHexadecimalDigit('5'));
    expect(GlobalCharTool.isHexadecimalDigit('6'));
    expect(GlobalCharTool.isHexadecimalDigit('7'));
    expect(GlobalCharTool.isHexadecimalDigit('8'));
    expect(GlobalCharTool.isHexadecimalDigit('9'));
    expect(GlobalCharTool.isHexadecimalDigit('A'));
    expect(GlobalCharTool.isHexadecimalDigit('B'));
    expect(GlobalCharTool.isHexadecimalDigit('C'));
    expect(GlobalCharTool.isHexadecimalDigit('D'));
    expect(GlobalCharTool.isHexadecimalDigit('E'));
    expect(GlobalCharTool.isHexadecimalDigit('F'));
  }

  //method
  @Test
  void testCase_isHexadecimalDigit_whenGivenCharacterIsNotAHexadecimalDigit() {
    expectNot(GlobalCharTool.isHexadecimalDigit('G'));
    expectNot(GlobalCharTool.isHexadecimalDigit('g'));
    expectNot(GlobalCharTool.isHexadecimalDigit('Z'));
    expectNot(GlobalCharTool.isHexadecimalDigit('z'));
    expectNot(GlobalCharTool.isHexadecimalDigit('%'));
    expectNot(GlobalCharTool.isHexadecimalDigit('&'));
    expectNot(GlobalCharTool.isHexadecimalDigit('.'));
    expectNot(GlobalCharTool.isHexadecimalDigit(','));
    expectNot(GlobalCharTool.isHexadecimalDigit('('));
    expectNot(GlobalCharTool.isHexadecimalDigit(')'));
  }
}
