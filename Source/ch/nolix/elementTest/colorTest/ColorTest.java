/*
 * file:	ColorTest.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	70
 */

//package declaration
package ch.nolix.elementTest.colorTest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.color.Color;

//test class
/**
 * This class is a test class for the color class.
 */
public final class ColorTest extends Test {
	
	public void test_createConstructor() {
		
		//exection
		final Color color = new Color(Color.ALICE_BLUE_INT);
	}
	
	//test method
	public void test_createInvertedColor_1() {
		
		//setup
		final Color color = new Color(0x000000);
		
		//execution
		final Color invertedColor = color.getInvertedColor();

		//verification
		expectThat(color.getValue()).equals(0x000000);
		expectThat(invertedColor.getValue()).equals(0xFFFFFF);
	}
	
	//test method
	public void test_createInvertedColor_2() {
		
		//setup
		final Color color = new Color(0xFFFFFF);
		
		//execution
		final Color invertedColor = color.getInvertedColor();

		//verification
		expectThat(color.getValue()).equals(0xFFFFFF);
		expectThat(invertedColor.getValue()).equals(0x000000);
	}
	
	//test method
	public void testEquals() {
		
		//setup
		final Color color = new Color();
		
		//execution and verification
		expectThat(color.equals(color));
	}

	//test method
	public void testGetType() {
		
		//setup
		final Color color = new Color();
		
		//execution and verification
		expectThat(color.getType()).equals("Color");
	}
	
	//test method
	public void testGetValues() {
		
		//setup
		final Color color = new Color("0x102030");
		
		//execution and verification
		expectThat(color.getRedValue()).equals(0x10);
		expectThat(color.getGreenValue()).equals(0x20);
		expectThat(color.getRedValue()).equals(0x30);
	}
}
