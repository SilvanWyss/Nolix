//package declaration
package ch.nolix.elementTest.colorTest;

import ch.nolix.element.color.Color;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A color test is a test for the color class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 150
 */
public final class ColorTest extends Test {
	
	//test case
	public void testCase_equals() {
		
		//setup
		final Color color = new Color();
		
		//execution
		final boolean equals = color.equals(color);
		
		//verification
		expect(equals);
	}
	
	//test case
	public void testGetBlueValue() {
		
		//setup
		final Color color = new Color("0x102030");
		
		//execution
		final int blueValue = color.getBlueValue();
		
		//execution & verification
		expect(blueValue).isEqualTo(0x30);
	}
	
	//test case
	public void testCase_getGreenValue() {
		
		//setup
		final Color color = new Color("0x102030");
		
		//execution
		final int greenValue = color.getGreenValue();
		
		//verification
		expect(greenValue).isEqualTo(0x20);
	}
	
	//test case
	public void testCase_getInvertedColor() {
		
		//setup
		final Color color = new Color(0x000000);
		
		//execution
		final Color invertedColor = color.getInvertedColor();

		//verification
		expect(invertedColor.getIntValue()).isEqualTo(0xFFFFFF);
	}
	
	//test case
	public void testCase_getInvertedColor_2() {
		
		//setup
		final Color color = new Color(0xFFFFFF);
		
		//execution
		final Color invertedColor = color.getInvertedColor();

		//verification
		expect(invertedColor.getIntValue()).isEqualTo(0x000000);
	}
	
	//test case
	public void testCase_getRedValue() {
		
		//setup
		final Color color = new Color("0x102030");
		
		//execution
		final int redValue = color.getRedValue();
		
		//verification
		expect(redValue).isEqualTo(0x10);
	}

	//test case
	public void testCase_getType() {
		
		//setup
		final Color color = new Color();
		
		//execution
		final String type = color.getType();
		
		//verification
		expect(type).isEqualTo("Color");
	}
	
	//test case
	public void testCase_stringConstructor() {
		
		//execution
		final Color color = new Color(Color.WHITE_STRING);
		
		//verification
		expect(color.getIntValue()).isEqualTo(Color.WHITE_INT);
	}
	
	//test case
	public void testCase_stringConstructor_2() {
		
		//execution
		final Color color = new Color(Color.BLACK_STRING);
		
		//verification
		expect(color.getIntValue()).isEqualTo(Color.BLACK_INT);
	}
	
	//test case
	public void testCase_toString() {
		
		//setup
		final Color color = new Color(Color.ALICE_BLUE_INT);
		
		//execution
		final String string = color.toString();
		
		//verification
		expect(string).isEqualTo("Color(AliceBlue)");
	}
	
	//test case
	public void testCase_toString_2() {
		
		//setup
		final Color color = new Color(Color.YELLOW_GREEN_INT);
		
		//execution
		final String string = color.toString();
		
		//verification
		expect(string).isEqualTo("Color(YellowGreen)");
	}
}
