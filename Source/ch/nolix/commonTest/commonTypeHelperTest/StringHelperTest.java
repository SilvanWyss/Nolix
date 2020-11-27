//package declaration
package ch.nolix.commonTest.commonTypeHelperTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.commonTypeHelper.StringHelper;
import ch.nolix.common.test.Test;

//class
/**
 * A {@link StringHelperTest} is a test for the {@link StringHelper}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 160
 */
public class StringHelperTest extends Test {
	
	//method
	@TestCase
	public void testCase_createStringWithoutLastCharacters_1A() {
		
		//execution
		final var result = StringHelper.createStringWithoutLastCharacters("cheeseburger", 0);
		
		//verification
		expect(result).isEqualTo("cheeseburger");
	}
	
	//method
	@TestCase
	public void testCase_createStringWithoutLastCharacters_1B() {
		
		//execution
		final var result = StringHelper.createStringWithoutLastCharacters("cheeseburger", 6);
		
		//verification
		expect(result).isEqualTo("cheese");
	}
	
	//method
	@TestCase
	public void testCase_createStringWithoutLastCharacters_1C() {
		
		//execution
		final var result = StringHelper.createStringWithoutLastCharacters("cheeseburger", 12);
		
		//verification
		expect(result).isEqualTo("");
	}
		
	//method
	@TestCase
	public void testCase_toPascalCase_1A() {
		
		//execution
		final var result = StringHelper.toPascalCase("Zebra");
		
		//verification
		expect(result).isEqualTo("Zebra");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_1B() {
		
		//execution
		final var result = StringHelper.toPascalCase("ZEBRA");
		
		//verification
		expect(result).isEqualTo("Zebra");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_2A() {
		
		//execution
		final var result = StringHelper.toPascalCase("CursorIcon");
		
		//verification
		expect(result).isEqualTo("CursorIcon");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_2B() {
		
		//execution
		final var result = StringHelper.toPascalCase("Cursor_Icon");
		
		//verification
		expect(result).isEqualTo("CursorIcon");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_2C() {
		
		//execution
		final var result = StringHelper.toPascalCase("CURSOR_ICON");
		
		//verification
		expect(result).isEqualTo("CursorIcon");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_whenStringIsEmpty() {
		
		//execution
		final var result = StringHelper.toPascalCase("");
		
		//verification
		expect(result).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_toInt() {
		
		expect(StringHelper.toInt("1")).isEqualTo(1);
		expect(StringHelper.toInt("10")).isEqualTo(10);
		expect(StringHelper.toInt("100")).isEqualTo(100);
		expect(StringHelper.toInt("1000")).isEqualTo(1000);
		
		expect(StringHelper.toInt("-1")).isEqualTo(-1);
		expect(StringHelper.toInt("-10")).isEqualTo(-10);
		expect(StringHelper.toInt("-100")).isEqualTo(-100);
		expect(StringHelper.toInt("-1000")).isEqualTo(-1000);
		
		expect(StringHelper.toInt("5")).isEqualTo(5);
		expect(StringHelper.toInt("55")).isEqualTo(55);
		expect(StringHelper.toInt("555")).isEqualTo(555);
		expect(StringHelper.toInt("5555")).isEqualTo(5555);
		
		expect(StringHelper.toInt("-5")).isEqualTo(-5);
		expect(StringHelper.toInt("-55")).isEqualTo(-55);
		expect(StringHelper.toInt("-555")).isEqualTo(-555);
		expect(StringHelper.toInt("-5555")).isEqualTo(-5555);
		
		expect(StringHelper.toInt("0x1")).isEqualTo(0x1);
		expect(StringHelper.toInt("0x10")).isEqualTo(0x10);
		expect( StringHelper.toInt("0x100")).isEqualTo(0x100);
		expect(StringHelper.toInt("0x1000")).isEqualTo(0x1000);
		
		expect(StringHelper.toInt("-0x1")).isEqualTo(-0x1);
		expect(StringHelper.toInt("-0x10")).isEqualTo(-0x10);
		expect( StringHelper.toInt("-0x100")).isEqualTo(-0x100);
		expect(StringHelper.toInt("-0x1000")).isEqualTo(-0x1000);
		
		expect(StringHelper.toInt("0x5")).isEqualTo(0x5);
		expect(StringHelper.toInt("0x55")).isEqualTo(0x55);
		expect(StringHelper.toInt("0x555")).isEqualTo(0x555);
		expect(StringHelper.toInt("0x5555")).isEqualTo(0x5555);
		
		expect(StringHelper.toInt("-0x5")).isEqualTo(-0x5);
		expect(StringHelper.toInt("-0x55")).isEqualTo(-0x55);
		expect(StringHelper.toInt("-0x555")).isEqualTo(-0x555);
		expect(StringHelper.toInt("-0x5555")).isEqualTo(-0x5555);
	}
}
