//package declaration
package ch.nolix.elementTest.colorTest;

//own imports
import ch.nolix.core.test.Test;
import ch.nolix.element.color.Color;

//test class
/**
 * A {@link ColorTest} is a test for {@link Color}s.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 220
 */
public final class ColorTest extends Test {
	
	//test case
	public void testCase_creation() {
		
		//execution
		final var color = new Color("0x000000");
		
		//verification
		expect(color.getIntValue()).isEqualTo(0);
		expect(color.hasFullAlphaValue());
	}

	//test case
	public void testCase_creation_2() {
		
		//execution
		final var color = new Color("0xFFFFFF");
		
		//verification
		expect(color.getIntValue()).isEqualTo(16777215);
		expect(color.hasFullAlphaValue());
	}

	//test case
	public void testCase_equals() {
		
		//setup
		final var color1 = new Color("0x102030");
		final var color2 = new Color("0x102030");
		
		//execution
		final var equals = color1.equals(color2);
		
		//verification
		expect(equals);
	}
	
	//test case
	public void testCase_getAlphaValue() {
		
		//setup
		final var color = new Color("0x102030A0");
		
		//execution
		final var alphaValue = color.getAlphaValue();
		
		//verification
		expect(alphaValue).isEqualTo(0xA0);
	}
	
	//test case
	public void testCase_getBlueValue() {
		
		//setup
		final var color = new Color("0x102030A0");
		
		//execution
		final var blueValue = color.getBlueValue();
		
		//verification
		expect(blueValue).isEqualTo(0x30);
	}
	
	//test case
	public void testCase_getGreenValue() {
		
		//setup
		final var color = new Color("0x102030A0");
		
		//execution
		final var greenValue = color.getGreenValue();
		
		//verification
		expect(greenValue).isEqualTo(0x20);
	}
	
	//test case
	public void testCase_getInvertedColor_whenTheGivenColorIsBlack() {
		
		//setup
		final var color = new Color(0x000000);
		
		//execution
		final var invertedColor = color.getInvertedColor();
		
		//verification
		expect(invertedColor.getIntValue()).isEqualTo(0xFFFFFF);
	}
	
	//test case
	public void testCase_getInvertedColor_whenTheGivenColorIsWhite() {
		
		//setup
		final var color = new Color(0xFFFFFF);
		
		//execution
		final var invertedColor = color.getInvertedColor();
		
		//verification
		expect(invertedColor.getIntValue()).isEqualTo(0x000000);
	}
	
	//test case
	public void testCase_getRedValue() {
		
		//setup
		final var color = new Color("0x102030A0");
		
		//execution
		final var redValue = color.getRedValue();
		
		//verification
		expect(redValue).isEqualTo(0x10);
	}
	
	//test case
	public void testCase_getSpecification() {
		
		//setup
		final var color = new Color("0x102030");
		
		//execution
		final var specification = color.getSpecification();
		
		//verification
		expect(specification.toString()).isEqualTo("Color(0x102030)");
	}
	
	//test case
	public void testCase_getSpecificationAs() {
		
		//setup
		final var color = new Color("0x102030");
		
		//execution
		final var specification = color.getSpecificationAs("BackgroundColor");
		
		//verification
		expect(specification.toString()).isEqualTo("BackgroundColor(0x102030)");
	}
	
	//test case
	public void testCase_getType() {
		
		//setup
		final var color = new Color();
		
		//execution
		final var type = color.getType();
		
		//verification
		expect(type).isEqualTo("Color");
	}
	
	//test case
	public void testCase_toColorWithFullAlphaValue() {
		
		//setup
		final var color = new Color("0x102030");
		
		//execution
		final var colorWithFullAlphaValue = color.toColorWithFullAlphaValue();
		
		//verification
		expect(colorWithFullAlphaValue.getAlphaValue()).isEqualTo(255);
	}
	
	//test case
	public void testCase_toColorWithFullAlphaValue_2() {
		
		//setup
		final var color = new Color("0x102030A0");
		
		//execution
		final var colorWithFullAlphaValue = color.toColorWithFullAlphaValue();
		
		//verification
		expect(colorWithFullAlphaValue.getAlphaValue()).isEqualTo(255);
	}
	
	//test case
	public void testCase_toString() {
		
		//setup
		final var color = new Color(Color.ALICE_BLUE_INT);
		
		//execution
		final var string = color.toString();
		
		//verification
		expect(string).isEqualTo("Color(AliceBlue)");
	}
	
	//test case
	public void testCase_toString_2() {
		
		//setup
		final var color = new Color(Color.YELLOW_GREEN_INT);
		
		//execution
		final var string = color.toString();
		
		//verification
		expect(string).isEqualTo("Color(YellowGreen)");
	}
}
