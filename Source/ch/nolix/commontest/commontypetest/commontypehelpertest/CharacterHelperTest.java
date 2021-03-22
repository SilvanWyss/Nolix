/*
 * file:	CharacterHelperTest.java
 * author:	Silvan Wyss
 * month:	05.03.2016
 * lines:	70
 */

//package declaration
package ch.nolix.commontest.commontypetest.commontypehelpertest;

import ch.nolix.common.commontype.commontypehelper.GlobalCharacterHelper;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.simpletest.SimpleTest;

//class
/**
* This class is a test class for the character helper class.
*/
public class CharacterHelperTest extends SimpleTest {

	//method
	@TestCase
	public void testIsDigit() {
		
		expectTrue(GlobalCharacterHelper.isDigit('0'));
		expectTrue(GlobalCharacterHelper.isDigit('1'));
		expectTrue(GlobalCharacterHelper.isDigit('2'));
		expectTrue(GlobalCharacterHelper.isDigit('3'));
		expectTrue(GlobalCharacterHelper.isDigit('4'));
		expectTrue(GlobalCharacterHelper.isDigit('5'));
		expectTrue(GlobalCharacterHelper.isDigit('6'));
		expectTrue(GlobalCharacterHelper.isDigit('7'));
		expectTrue(GlobalCharacterHelper.isDigit('8'));
		expectTrue(GlobalCharacterHelper.isDigit('9'));
		
		expectFalse(GlobalCharacterHelper.isDigit('A'));
		expectFalse(GlobalCharacterHelper.isDigit('a'));
		expectFalse(GlobalCharacterHelper.isDigit('Z'));
		expectFalse(GlobalCharacterHelper.isDigit('z'));
		expectFalse(GlobalCharacterHelper.isDigit('%'));
		expectFalse(GlobalCharacterHelper.isDigit('&'));
		expectFalse(GlobalCharacterHelper.isDigit('.'));
		expectFalse(GlobalCharacterHelper.isDigit(','));
		expectFalse(GlobalCharacterHelper.isDigit('('));
		expectFalse(GlobalCharacterHelper.isDigit(')'));
	}
	
	//method
	@TestCase
	public void testIsHexadeciumalDigit() {
		
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('0'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('1'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('2'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('3'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('4'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('5'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('6'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('7'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('8'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('9'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('A'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('B'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('C'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('D'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('E'));
		expectTrue(GlobalCharacterHelper.isHexadecimalDigit('F'));
	
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit('G'));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit('g'));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit('Z'));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit('z'));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit('%'));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit('&'));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit('.'));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit(','));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit('('));
		expectFalse(GlobalCharacterHelper.isHexadecimalDigit(')'));
	}
}
