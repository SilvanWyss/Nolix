/*
 * file:	CharacterHelperTest.java
 * author:	Silvan Wyss
 * month:	05.03.2016
 * lines:	70
 */

//package declaration
package ch.nolix.coreTest.helperTest;

import ch.nolix.core.commonTypeHelpers.CharacterHelper;
import ch.nolix.core.test2.Test;

//test class
/**
* This class is a test class for the character helper class.
*/
public class CharacterHelperTest extends Test {

	//test case
	public void testIsDigit() {
		
		expectTrue(CharacterHelper.isDigit('0'));
		expectTrue(CharacterHelper.isDigit('1'));
		expectTrue(CharacterHelper.isDigit('2'));
		expectTrue(CharacterHelper.isDigit('3'));
		expectTrue(CharacterHelper.isDigit('4'));
		expectTrue(CharacterHelper.isDigit('5'));
		expectTrue(CharacterHelper.isDigit('6'));
		expectTrue(CharacterHelper.isDigit('7'));
		expectTrue(CharacterHelper.isDigit('8'));
		expectTrue(CharacterHelper.isDigit('9'));
		
		expectFalse(CharacterHelper.isDigit('A'));
		expectFalse(CharacterHelper.isDigit('a'));
		expectFalse(CharacterHelper.isDigit('Z'));
		expectFalse(CharacterHelper.isDigit('z'));
		expectFalse(CharacterHelper.isDigit('%'));
		expectFalse(CharacterHelper.isDigit('&'));
		expectFalse(CharacterHelper.isDigit('.'));
		expectFalse(CharacterHelper.isDigit(','));
		expectFalse(CharacterHelper.isDigit('('));
		expectFalse(CharacterHelper.isDigit(')'));
	}
	
	//test case
	public void testIsHexadeciumalDigit() {
		
		expectTrue(CharacterHelper.isHexadecimalDigit('0'));
		expectTrue(CharacterHelper.isHexadecimalDigit('1'));
		expectTrue(CharacterHelper.isHexadecimalDigit('2'));
		expectTrue(CharacterHelper.isHexadecimalDigit('3'));
		expectTrue(CharacterHelper.isHexadecimalDigit('4'));
		expectTrue(CharacterHelper.isHexadecimalDigit('5'));
		expectTrue(CharacterHelper.isHexadecimalDigit('6'));
		expectTrue(CharacterHelper.isHexadecimalDigit('7'));
		expectTrue(CharacterHelper.isHexadecimalDigit('8'));
		expectTrue(CharacterHelper.isHexadecimalDigit('9'));
		expectTrue(CharacterHelper.isHexadecimalDigit('A'));
		expectTrue(CharacterHelper.isHexadecimalDigit('B'));
		expectTrue(CharacterHelper.isHexadecimalDigit('C'));
		expectTrue(CharacterHelper.isHexadecimalDigit('D'));
		expectTrue(CharacterHelper.isHexadecimalDigit('E'));
		expectTrue(CharacterHelper.isHexadecimalDigit('F'));
	
		expectFalse(CharacterHelper.isHexadecimalDigit('G'));
		expectFalse(CharacterHelper.isHexadecimalDigit('g'));
		expectFalse(CharacterHelper.isHexadecimalDigit('Z'));
		expectFalse(CharacterHelper.isHexadecimalDigit('z'));
		expectFalse(CharacterHelper.isHexadecimalDigit('%'));
		expectFalse(CharacterHelper.isHexadecimalDigit('&'));
		expectFalse(CharacterHelper.isHexadecimalDigit('.'));
		expectFalse(CharacterHelper.isHexadecimalDigit(','));
		expectFalse(CharacterHelper.isHexadecimalDigit('('));
		expectFalse(CharacterHelper.isHexadecimalDigit(')'));
	}
}
