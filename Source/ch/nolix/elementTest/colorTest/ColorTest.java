//package declaration
package ch.nolix.elementTest.colorTest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.color.Color;

//test class
/**
 * A color test is a test for the color class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 150
 */
public final class ColorTest extends Test {
	
	//test method
	public void test_equals() {
		
		//setup
		final Color color = new Color();
		
		//execution
		final boolean equals = color.equals(color);
		
		//verification
		expect(equals);
	}
	
	//test method
	public void testGetBlueValue() {
		
		//setup
		final Color color = new Color("0x102030");
		
		//execution
		final int blueValue = color.getBlueValue();
		
		//execution and verification
		expect(blueValue).isEqualTo(0x30);
	}
	
	//test method
	public void test_getGreenValue() {
		
		//setup
		final Color color = new Color("0x102030");
		
		//execution
		final int greenValue = color.getGreenValue();
		
		//verification
		expect(greenValue).isEqualTo(0x20);
	}
	
	//test method
	public void test_getInvertedColor() {
		
		//setup
		final Color color = new Color(0x000000);
		
		//execution
		final Color invertedColor = color.getInvertedColor();

		//verification
		expect(invertedColor.getValue()).isEqualTo(0xFFFFFF);
	}
	
	//test method
	public void test_getInvertedColor_2() {
		
		//setup
		final Color color = new Color(0xFFFFFF);
		
		//execution
		final Color invertedColor = color.getInvertedColor();

		//verification
		expect(invertedColor.getValue()).isEqualTo(0x000000);
	}
	
	//test method
	public void test_getRedValue() {
		
		//setup
		final Color color = new Color("0x102030");
		
		//execution
		final int redValue = color.getRedValue();
		
		//verification
		expect(redValue).isEqualTo(0x10);
	}

	//test method
	public void test_getType() {
		
		//setup
		final Color color = new Color();
		
		//execution
		final String type = color.getType();
		
		//verification
		expect(type).equals("Color");
	}
	
	//test method
	public void test_stringConstructor() {
		
		//execution
		final Color color = new Color(Color.WHITE_STRING);
		
		//verification
		expect(color.getValue()).isEqualTo(Color.WHITE_INT);
	}
	
	//test method
	public void test_stringConstructor_2() {
		
		//execution
		final Color color = new Color(Color.BLACK_STRING);
		
		//verification
		expect(color.getValue()).isEqualTo(Color.BLACK_INT);
	}
	
	//test method
	public void test_toString() {
		
		//setup
		final Color color = new Color(Color.ALICE_BLUE_INT);
		
		//execution
		final String string = color.toString();
		
		//verification
		expect(string).equals("Color(AliceBlue)");
	}
	
	//test method
	public void test_toString_2() {
		
		//setup
		final Color color = new Color(Color.YELLOW_GREEN_INT);
		
		//execution
		final String string = color.toString();
		
		//verification
		expect(string).equals("Color(YellowGreen)");
	}
}
