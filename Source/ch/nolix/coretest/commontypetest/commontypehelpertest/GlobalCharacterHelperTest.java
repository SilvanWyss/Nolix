//package declaration
package ch.nolix.coretest.commontypetest.commontypehelpertest;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalCharacterHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class GlobalCharacterHelperTest extends Test {

  //method
  @TestCase
  public void testCase_isDigit_whenGivenCharacterIsADigit() {
    expect(GlobalCharacterHelper.isDigit('0'));
    expect(GlobalCharacterHelper.isDigit('1'));
    expect(GlobalCharacterHelper.isDigit('2'));
    expect(GlobalCharacterHelper.isDigit('3'));
    expect(GlobalCharacterHelper.isDigit('4'));
    expect(GlobalCharacterHelper.isDigit('5'));
    expect(GlobalCharacterHelper.isDigit('6'));
    expect(GlobalCharacterHelper.isDigit('7'));
    expect(GlobalCharacterHelper.isDigit('8'));
    expect(GlobalCharacterHelper.isDigit('9'));
  }

  //method
  @TestCase
  public void testCase_isDigit_whenGivenCharacterIsNotADigit() {
    expectNot(GlobalCharacterHelper.isDigit('A'));
    expectNot(GlobalCharacterHelper.isDigit('a'));
    expectNot(GlobalCharacterHelper.isDigit('Z'));
    expectNot(GlobalCharacterHelper.isDigit('z'));
    expectNot(GlobalCharacterHelper.isDigit('%'));
    expectNot(GlobalCharacterHelper.isDigit('&'));
    expectNot(GlobalCharacterHelper.isDigit('.'));
    expectNot(GlobalCharacterHelper.isDigit(','));
    expectNot(GlobalCharacterHelper.isDigit('('));
    expectNot(GlobalCharacterHelper.isDigit(')'));
  }

  //method
  @TestCase
  public void testCase_isHexadecimalDigit_whenGivenCharacterIsAHexadecimalDigit() {
    expect(GlobalCharacterHelper.isHexadecimalDigit('0'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('1'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('2'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('3'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('4'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('5'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('6'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('7'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('8'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('9'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('A'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('B'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('C'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('D'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('E'));
    expect(GlobalCharacterHelper.isHexadecimalDigit('F'));
  }

  //method
  @TestCase
  public void testCase_isHexadecimalDigit_whenGivenCharacterIsNotAHexadecimalDigit() {
    expectNot(GlobalCharacterHelper.isHexadecimalDigit('G'));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit('g'));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit('Z'));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit('z'));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit('%'));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit('&'));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit('.'));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit(','));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit('('));
    expectNot(GlobalCharacterHelper.isHexadecimalDigit(')'));
  }
}
