/*
 * file:	StringHelperTest.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	80
 */

//package declaration
package ch.nolix.coreTest.helperTest;

//own imports
import ch.nolix.core.helper.StringHelper;
import ch.nolix.primitive.test.Test;

//test class
/**
 * This class is a test class for the string helper class.
 */
public class StringHelperTest extends Test {

	//test method
	public void testToInteger1() {
		expectEquality(1, StringHelper.toInt("1"));
		expectEquality(10, StringHelper.toInt("10"));
		expectEquality(100, StringHelper.toInt("100"));
		expectEquality(1000, StringHelper.toInt("1000"));
	}
	
	//test method
	public void testToInteger2() {
		expectEquality(2, StringHelper.toInt("2"));
		expectEquality(24, StringHelper.toInt("24"));
		expectEquality(246, StringHelper.toInt("246"));
		expectEquality(2468, StringHelper.toInt("2468"));
	}
	
	//test method
	public void testToInteger3() {
		expectEquality(-1, StringHelper.toInt("-1"));
		expectEquality(-10, StringHelper.toInt("-10"));
		expectEquality(-100, StringHelper.toInt("-100"));
		expectEquality(-1000, StringHelper.toInt("-1000"));
	}
	
	//test method
	public void testToInteger4() {
		expectEquality(-2, StringHelper.toInt("-2"));
		expectEquality(-24, StringHelper.toInt("-24"));
		expectEquality(-246, StringHelper.toInt("-246"));
		expectEquality(-2468, StringHelper.toInt("-2468"));
	}
	
	//test method
	public void testToInteger5() {
		expectEquality(1, StringHelper.toInt("0x1"));
		expectEquality(16, StringHelper.toInt("0x10"));
		expectEquality(256, StringHelper.toInt("0x100"));
		expectEquality(4096, StringHelper.toInt("0x1000"));
	}
	
	//test method
	public void testToInteger6() {
		expectEquality(2, StringHelper.toInt("0x2"));
		expectEquality(36, StringHelper.toInt("0x24"));
		expectEquality(582, StringHelper.toInt("0x246"));
		expectEquality(9320, StringHelper.toInt("0x2468"));
	}

	//test method
	public void testToInteger7() {
		expectEquality(-1, StringHelper.toInt("-0x1"));
		expectEquality(-16, StringHelper.toInt("-0x10"));
		expectEquality(-256, StringHelper.toInt("-0x100"));
		expectEquality(-4096, StringHelper.toInt("-0x1000"));
	}
	
	//test method
	public void testToInteger8() {
		expectEquality(-2, StringHelper.toInt("-0x2"));
		expectEquality(-36, StringHelper.toInt("-0x24"));
		expectEquality(-582, StringHelper.toInt("-0x246"));
		expectEquality(-9320, StringHelper.toInt("-0x2468"));
	}
}
