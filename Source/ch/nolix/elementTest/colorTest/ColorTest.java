//package declaration
package ch.nolix.elementTest.colorTest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.color.Color;

//test class
/**
 * This class is a test class for the color class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 */
public final class ColorTest extends Test {
	
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
		expect(color.getType()).equals("Color");
	}
	
	//test method
	public void testGetValue() {
		
		//setup
		final Color color = new Color("0x102030");
		
		//execution and verification
		expect(color.getRedValue()).isEqualTo(0x10);
		expect(color.getGreenValue()).isEqualTo(0x20);
		expect(color.getRedValue()).isEqualTo(0x30);
	}
}
