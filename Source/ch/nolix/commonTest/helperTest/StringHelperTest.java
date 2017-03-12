/*
 * file:	StringHelperTest.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	80
 */

//package declaration
package ch.nolix.commonTest.helperTest;

//own imports
import ch.nolix.common.helper.StringHelper;
import ch.nolix.common.test.StandardTest;

//test class
/**
 * This class is a test class for the string helper class.
 */
public class StringHelperTest extends StandardTest {

	//test method
	public void testToInteger1() {
		expectEquality(1, StringHelper.toInteger("1"));
		expectEquality(10, StringHelper.toInteger("10"));
		expectEquality(100, StringHelper.toInteger("100"));
		expectEquality(1000, StringHelper.toInteger("1000"));
	}
	
	//test method
	public void testToInteger2() {
		expectEquality(2, StringHelper.toInteger("2"));
		expectEquality(24, StringHelper.toInteger("24"));
		expectEquality(246, StringHelper.toInteger("246"));
		expectEquality(2468, StringHelper.toInteger("2468"));
	}
	
	//test method
	public void testToInteger3() {
		expectEquality(-1, StringHelper.toInteger("-1"));
		expectEquality(-10, StringHelper.toInteger("-10"));
		expectEquality(-100, StringHelper.toInteger("-100"));
		expectEquality(-1000, StringHelper.toInteger("-1000"));
	}
	
	//test method
	public void testToInteger4() {
		expectEquality(-2, StringHelper.toInteger("-2"));
		expectEquality(-24, StringHelper.toInteger("-24"));
		expectEquality(-246, StringHelper.toInteger("-246"));
		expectEquality(-2468, StringHelper.toInteger("-2468"));
	}
	
	//test method
	public void testToInteger5() {
		expectEquality(1, StringHelper.toInteger("0x1"));
		expectEquality(16, StringHelper.toInteger("0x10"));
		expectEquality(256, StringHelper.toInteger("0x100"));
		expectEquality(4096, StringHelper.toInteger("0x1000"));
	}
	
	//test method
	public void testToInteger6() {
		expectEquality(2, StringHelper.toInteger("0x2"));
		expectEquality(36, StringHelper.toInteger("0x24"));
		expectEquality(582, StringHelper.toInteger("0x246"));
		expectEquality(9320, StringHelper.toInteger("0x2468"));
	}

	//test method
	public void testToInteger7() {
		expectEquality(-1, StringHelper.toInteger("-0x1"));
		expectEquality(-16, StringHelper.toInteger("-0x10"));
		expectEquality(-256, StringHelper.toInteger("-0x100"));
		expectEquality(-4096, StringHelper.toInteger("-0x1000"));
	}
	
	//test method
	public void testToInteger8() {
		expectEquality(-2, StringHelper.toInteger("-0x2"));
		expectEquality(-36, StringHelper.toInteger("-0x24"));
		expectEquality(-582, StringHelper.toInteger("-0x246"));
		expectEquality(-9320, StringHelper.toInteger("-0x2468"));
	}
}
