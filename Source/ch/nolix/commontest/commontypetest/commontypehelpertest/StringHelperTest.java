//package declaration
package ch.nolix.commontest.commontypetest.commontypehelpertest;

import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

//class
/**
 * A {@link StringHelperTest} is a test for the {@link GlobalStringHelper}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 250
 */
public class StringHelperTest extends Test {
	
	//method
	@TestCase
	public void testCase_createStringWithoutLastCharacters_1A() {
		
		//execution
		final var result = GlobalStringHelper.createStringWithoutLastCharacters("cheeseburger", 0);
		
		//verification
		expect(result).isEqualTo("cheeseburger");
	}
	
	//method
	@TestCase
	public void testCase_createStringWithoutLastCharacters_1B() {
		
		//execution
		final var result = GlobalStringHelper.createStringWithoutLastCharacters("cheeseburger", 6);
		
		//verification
		expect(result).isEqualTo("cheese");
	}
	
	//method
	@TestCase
	public void testCase_createStringWithoutLastCharacters_1C() {
		
		//execution
		final var result = GlobalStringHelper.createStringWithoutLastCharacters("cheeseburger", 12);
		
		//verification
		expect(result).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_toCapitalSnakeCase_1A() {
		
		//execution
		final var result = GlobalStringHelper.toCapitalSnakeCase("Zebra");
		
		//verification
		expect(result).isEqualTo("ZEBRA");
	}
	
	//method
	@TestCase
	public void testCase_toCapitalSnakeCase_1B() {
		
		//execution
		final var result = GlobalStringHelper.toCapitalSnakeCase("zebra");
		
		//verification
		expect(result).isEqualTo("ZEBRA");
	}
	
	//method
	@TestCase
	public void testCase_toCapitalSnakeCase_1C() {
		
		//execution
		final var result = GlobalStringHelper.toCapitalSnakeCase("ZEBRA");
		
		//verification
		expect(result).isEqualTo("ZEBRA");
	}
	
	//method
	@TestCase
	public void testCase_toCapitalSnakeCase_2A() {
		
		//execution
		final var result = GlobalStringHelper.toCapitalSnakeCase("Cursor_Icon");
		
		//verification
		expect(result).isEqualTo("CURSOR_ICON");
	}
	
	//method
	@TestCase
	public void testCase_toCapitalSnakeCase_2B() {
		
		//execution
		final var result = GlobalStringHelper.toCapitalSnakeCase("cursor_icon");
		
		//verification
		expect(result).isEqualTo("CURSOR_ICON");
	}
	
	//method
	@TestCase
	public void testCase_toCapitalSnakeCase_2C() {
		
		//execution
		final var result = GlobalStringHelper.toCapitalSnakeCase("CURSOR_ICON");
		
		//verification
		expect(result).isEqualTo("CURSOR_ICON");
	}
	
	//method
	@TestCase
	public void testCase_toCapitalSnakeCase_whenStringIsEmpty() {
		
		//execution
		final var result = GlobalStringHelper.toCapitalSnakeCase("");
		
		//verification
		expect(result).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_toInt() {
		
		expect(GlobalStringHelper.toInt("1")).isEqualTo(1);
		expect(GlobalStringHelper.toInt("10")).isEqualTo(10);
		expect(GlobalStringHelper.toInt("100")).isEqualTo(100);
		expect(GlobalStringHelper.toInt("1000")).isEqualTo(1000);
		
		expect(GlobalStringHelper.toInt("-1")).isEqualTo(-1);
		expect(GlobalStringHelper.toInt("-10")).isEqualTo(-10);
		expect(GlobalStringHelper.toInt("-100")).isEqualTo(-100);
		expect(GlobalStringHelper.toInt("-1000")).isEqualTo(-1000);
		
		expect(GlobalStringHelper.toInt("5")).isEqualTo(5);
		expect(GlobalStringHelper.toInt("55")).isEqualTo(55);
		expect(GlobalStringHelper.toInt("555")).isEqualTo(555);
		expect(GlobalStringHelper.toInt("5555")).isEqualTo(5555);
		
		expect(GlobalStringHelper.toInt("-5")).isEqualTo(-5);
		expect(GlobalStringHelper.toInt("-55")).isEqualTo(-55);
		expect(GlobalStringHelper.toInt("-555")).isEqualTo(-555);
		expect(GlobalStringHelper.toInt("-5555")).isEqualTo(-5555);
		
		expect(GlobalStringHelper.toInt("0x1")).isEqualTo(0x1);
		expect(GlobalStringHelper.toInt("0x10")).isEqualTo(0x10);
		expect( GlobalStringHelper.toInt("0x100")).isEqualTo(0x100);
		expect(GlobalStringHelper.toInt("0x1000")).isEqualTo(0x1000);
		
		expect(GlobalStringHelper.toInt("-0x1")).isEqualTo(-0x1);
		expect(GlobalStringHelper.toInt("-0x10")).isEqualTo(-0x10);
		expect( GlobalStringHelper.toInt("-0x100")).isEqualTo(-0x100);
		expect(GlobalStringHelper.toInt("-0x1000")).isEqualTo(-0x1000);
		
		expect(GlobalStringHelper.toInt("0x5")).isEqualTo(0x5);
		expect(GlobalStringHelper.toInt("0x55")).isEqualTo(0x55);
		expect(GlobalStringHelper.toInt("0x555")).isEqualTo(0x555);
		expect(GlobalStringHelper.toInt("0x5555")).isEqualTo(0x5555);
		
		expect(GlobalStringHelper.toInt("-0x5")).isEqualTo(-0x5);
		expect(GlobalStringHelper.toInt("-0x55")).isEqualTo(-0x55);
		expect(GlobalStringHelper.toInt("-0x555")).isEqualTo(-0x555);
		expect(GlobalStringHelper.toInt("-0x5555")).isEqualTo(-0x5555);
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_1A() {
		
		//execution
		final var result = GlobalStringHelper.toPascalCase("Zebra");
		
		//verification
		expect(result).isEqualTo("Zebra");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_1B() {
		
		//execution
		final var result = GlobalStringHelper.toPascalCase("zebra");
		
		//verification
		expect(result).isEqualTo("Zebra");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_1C() {
		
		//execution
		final var result = GlobalStringHelper.toPascalCase("ZEBRA");
		
		//verification
		expect(result).isEqualTo("Zebra");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_2A() {
		
		//execution
		final var result = GlobalStringHelper.toPascalCase("Cursor_Icon");
		
		//verification
		expect(result).isEqualTo("CursorIcon");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_2B() {
		
		//execution
		final var result = GlobalStringHelper.toPascalCase("cursor_icon");
		
		//verification
		expect(result).isEqualTo("CursorIcon");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_2C() {
		
		//execution
		final var result = GlobalStringHelper.toPascalCase("CURSOR_ICON");
		
		//verification
		expect(result).isEqualTo("CursorIcon");
	}
	
	//method
	@TestCase
	public void testCase_toPascalCase_whenStringIsEmpty() {
		
		//execution
		final var result = GlobalStringHelper.toPascalCase("");
		
		//verification
		expect(result).isEqualTo("");
	}
}
