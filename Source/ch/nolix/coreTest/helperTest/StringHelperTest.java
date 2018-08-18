//package declaration
package ch.nolix.coreTest.helperTest;

//own imports
import ch.nolix.core.helper.StringHelper;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A {@link StringHelperTest} is a test for the {@link StringHelper} class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
public class StringHelperTest extends Test {
	
	//test method
	public void test_toInt_1() {
		expect(StringHelper.toInt("1")).isEqualTo(1);
		expect(StringHelper.toInt("10")).isEqualTo(10);
		expect(StringHelper.toInt("100")).isEqualTo(100);
		expect(StringHelper.toInt("1000")).isEqualTo(1000);
	}
	
	//test method
	public void test_toInt_2() {
		expect(StringHelper.toInt("-1")).isEqualTo(-1);
		expect(StringHelper.toInt("-10")).isEqualTo(-10);
		expect(StringHelper.toInt("-100")).isEqualTo(-100);
		expect(StringHelper.toInt("-1000")).isEqualTo(-1000);
	}
	
	//test method
	public void test_toInt_3() {
		expect(StringHelper.toInt("5")).isEqualTo(5);
		expect(StringHelper.toInt("55")).isEqualTo(55);
		expect(StringHelper.toInt("555")).isEqualTo(555);
		expect(StringHelper.toInt("5555")).isEqualTo(5555);
	}
	
	//test method
	public void test_toInt_4() {
		expect(StringHelper.toInt("-5")).isEqualTo(-5);
		expect(StringHelper.toInt("-55")).isEqualTo(-55);
		expect(StringHelper.toInt("-555")).isEqualTo(-555);
		expect(StringHelper.toInt("-5555")).isEqualTo(-5555);
	}
	
	//test method
	public void test_toInt_5() {
		expect(StringHelper.toInt("0x1")).isEqualTo(0x1);
		expect(StringHelper.toInt("0x10")).isEqualTo(0x10);
		expect( StringHelper.toInt("0x100")).isEqualTo(0x100);
		expect(StringHelper.toInt("0x1000")).isEqualTo(0x1000);
	}
	
	//test method
	public void test_toInt6() {
		expect(StringHelper.toInt("-0x1")).isEqualTo(-0x1);
		expect(StringHelper.toInt("-0x10")).isEqualTo(-0x10);
		expect( StringHelper.toInt("-0x100")).isEqualTo(-0x100);
		expect(StringHelper.toInt("-0x1000")).isEqualTo(-0x1000);
	}

	//test method
	public void test_toInt_7() {
		expect(StringHelper.toInt("0x5")).isEqualTo(0x5);
		expect(StringHelper.toInt("0x55")).isEqualTo(0x55);
		expect(StringHelper.toInt("0x555")).isEqualTo(0x555);
		expect(StringHelper.toInt("0x5555")).isEqualTo(0x5555);
	}
	
	//test method
	public void test_toInt_8() {
		expect(StringHelper.toInt("-0x5")).isEqualTo(-0x5);
		expect(StringHelper.toInt("-0x55")).isEqualTo(-0x55);
		expect(StringHelper.toInt("-0x555")).isEqualTo(-0x555);
		expect(StringHelper.toInt("-0x5555")).isEqualTo(-0x5555);
	}
}
