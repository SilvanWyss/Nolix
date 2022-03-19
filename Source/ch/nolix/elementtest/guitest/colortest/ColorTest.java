//package declaration
package ch.nolix.elementtest.guitest.colortest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.element.gui.color.Color;

//class
/**
 * A {@link ColorTest} is a test for {@link Color}s.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class ColorTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var color = Color.fromString("0x000000");
		
		//verification
		expect(color.toValue()).isEqualTo(0);
		expect(color.hasFullAlphaValue());
	}

	//method
	@TestCase
	public void testCase_creation_2() {
		
		//execution
		final var color = Color.fromString("0xFFFFFF");
		
		//verification
		expect(color.toValue()).isEqualTo(16_777_215);
		expect(color.hasFullAlphaValue());
	}

	//method
	@TestCase
	public void testCase_equals() {
		
		//setup
		final var color1 = Color.fromString("0x102030");
		final var color2 = Color.fromString("0x102030");
		
		//execution
		final var equals = color1.equals(color2);
		
		//verification
		expect(equals);
	}
	
	//method
	@TestCase
	public void testCase_getAlphaValue() {
		
		//setup
		final var color = Color.fromString("0x102030A0");
		
		//execution
		final var alphaValue = color.getAlphaValue();
		
		//verification
		expect(alphaValue).isEqualTo(0xA0);
	}
	
	//method
	@TestCase
	public void testCase_getBlueValue() {
		
		//setup
		final var color = Color.fromString("0x102030A0");
		
		//execution
		final var blueValue = color.getBlueValue();
		
		//verification
		expect(blueValue).isEqualTo(0x30);
	}
	
	//method
	@TestCase
	public void testCase_getGreenValue() {
		
		//setup
		final var color = Color.fromString("0x102030A0");
		
		//execution
		final var greenValue = color.getGreenValue();
		
		//verification
		expect(greenValue).isEqualTo(0x20);
	}
	
	//method
	@TestCase
	public void testCase_getHexadecimalValueOrColorName() {
		
		//setup
		final var color = Color.fromValue(Color.ALICE_BLUE_INT);
		
		//execution
		final var result = color.getHexadecimalValueOrColorName();
		
		//verification
		expect(result).isEqualTo("AliceBlue");
	}

	//method
	@TestCase
	public void testCase_getHexadecimalValueOrColorName_2() {
		
		//setup
		final var color = Color.fromValue(Color.YELLOW_GREEN_INT);
		
		//execution
		final var result = color.getHexadecimalValueOrColorName();
		
		//verification
		expect(result).isEqualTo("YellowGreen");
	}

	//method
	@TestCase
	public void testCase_getInvertedColor_whenTheGivenColorIsBlack() {
		
		//setup
		final var color = Color.fromValue(0x000000);
		
		//execution
		final var invertedColor = color.getInvertedColor();
		
		//verification
		expect(invertedColor.toValue()).isEqualTo(0xFFFFFF);
	}
	
	//method
	@TestCase
	public void testCase_getInvertedColor_whenTheGivenColorIsWhite() {
		
		//setup
		final var color = Color.fromValue(0xFFFFFF);
		
		//execution
		final var invertedColor = color.getInvertedColor();
		
		//verification
		expect(invertedColor.toValue()).isEqualTo(0x000000);
	}
	
	//method
	@TestCase
	public void testCase_getRedValue() {
		
		//setup
		final var color = Color.fromString("0x102030A0");
		
		//execution
		final var redValue = color.getRedValue();
		
		//verification
		expect(redValue).isEqualTo(0x10);
	}
	
	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//setup
		final var color = Color.fromString("0x102030");
		
		//execution
		final var specification = color.getSpecification();
		
		//verification
		expect(specification.toString()).isEqualTo("Color(0x102030)");
	}
	
	//method
	@TestCase
	public void testCase_getSpecificationAs() {
		
		//setup
		final var color = Color.fromString("0x102030");
		
		//execution
		final var specification = color.getSpecificationAs("BackgroundColor");
		
		//verification
		expect(specification.toString()).isEqualTo("BackgroundColor(0x102030)");
	}
	
	//method
	@TestCase
	public void testCase_toColorWithFullAlphaValue() {
		
		//setup
		final var color = Color.fromString("0x102030");
		
		//execution
		final var colorWithFullAlphaValue = color.toColorWithFullAlphaValue();
		
		//verification
		expect(colorWithFullAlphaValue.getAlphaValue()).isEqualTo(255);
	}
	
	//method
	@TestCase
	public void testCase_toColorWithFullAlphaValue_2() {
		
		//setup
		final var color = Color.fromString("0x102030A0");
		
		//execution
		final var colorWithFullAlphaValue = color.toColorWithFullAlphaValue();
		
		//verification
		expect(colorWithFullAlphaValue.getAlphaValue()).isEqualTo(255);
	}
}
