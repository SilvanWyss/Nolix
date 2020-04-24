//package declaration
package ch.nolix.commonTest.commonTypeHelperTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.commonTypeHelper.StringHelper;
import ch.nolix.common.test.Test;

//class
/**
 * A {@link StringHelperTest} is a test for the {@link StringHelper} class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 */
public class StringHelperTest extends Test {
	
	//method
	@TestCase
	public void testCase_createStringWithoutLastCharacters() {
		
		expect(StringHelper.createStringWithoutLastCharacters("cheeseburger", 0))
		.isEqualTo("cheeseburger");
		
		expect(StringHelper.createStringWithoutLastCharacters("cheeseburger", 6))
		.isEqualTo("cheese");
		
		expect(StringHelper.createStringWithoutLastCharacters("cheeseburger", 12))
		.isEmpty();
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
