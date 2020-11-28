//package declaration
package ch.nolix.commonTest.commonTypeWrapperTest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.commontypewrapper.ByteWrapper;
import ch.nolix.common.test.Test;

//class
public final class WrapperByteTest extends Test {
	
	//method
	@TestCase
	public void testCase_getBitAtAsInt() {
		
		//setup
		final var testUnit = new ByteWrapper(0, 0, 0, 0, 0, 0, 0, 0);
		
		//execution
		final var resultBit1 = testUnit.getBitAtAsInt(1);
		final var resultBit2 = testUnit.getBitAtAsInt(2);
		final var resultBit3 = testUnit.getBitAtAsInt(3);
		final var resultBit4 = testUnit.getBitAtAsInt(4);
		final var resultBit5 = testUnit.getBitAtAsInt(5);
		final var resultBit6 = testUnit.getBitAtAsInt(6);
		final var resultBit7 = testUnit.getBitAtAsInt(7);
		final var resultBit8 = testUnit.getBitAtAsInt(8);
		
		//verification
		expect(resultBit1).isEqualTo(0);
		expect(resultBit2).isEqualTo(0);
		expect(resultBit3).isEqualTo(0);
		expect(resultBit4).isEqualTo(0);
		expect(resultBit5).isEqualTo(0);
		expect(resultBit6).isEqualTo(0);
		expect(resultBit7).isEqualTo(0);
		expect(resultBit8).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_getBitAtAsInt_2() {
		
		//setup
		final var testUnit = new ByteWrapper(0, 0, 0, 0, 0, 1, 1, 1);
		
		//execution
		final var resultBit1 = testUnit.getBitAtAsInt(1);
		final var resultBit2 = testUnit.getBitAtAsInt(2);
		final var resultBit3 = testUnit.getBitAtAsInt(3);
		final var resultBit4 = testUnit.getBitAtAsInt(4);
		final var resultBit5 = testUnit.getBitAtAsInt(5);
		final var resultBit6 = testUnit.getBitAtAsInt(6);
		final var resultBit7 = testUnit.getBitAtAsInt(7);
		final var resultBit8 = testUnit.getBitAtAsInt(8);
		
		//verification
		expect(resultBit1).isEqualTo(0);
		expect(resultBit2).isEqualTo(0);
		expect(resultBit3).isEqualTo(0);
		expect(resultBit4).isEqualTo(0);
		expect(resultBit5).isEqualTo(0);
		expect(resultBit6).isEqualTo(1);
		expect(resultBit7).isEqualTo(1);
		expect(resultBit8).isEqualTo(1);
	}
	
	//method
	@TestCase
	public void testCase_getBitAtAsInt_3() {
		
		//setup
		final var testUnit = new ByteWrapper(1, 1, 1, 0, 0, 0, 0, 0);
		
		//execution
		final var resultBit1 = testUnit.getBitAtAsInt(1);
		final var resultBit2 = testUnit.getBitAtAsInt(2);
		final var resultBit3 = testUnit.getBitAtAsInt(3);
		final var resultBit4 = testUnit.getBitAtAsInt(4);
		final var resultBit5 = testUnit.getBitAtAsInt(5);
		final var resultBit6 = testUnit.getBitAtAsInt(6);
		final var resultBit7 = testUnit.getBitAtAsInt(7);
		final var resultBit8 = testUnit.getBitAtAsInt(8);
		
		//verification
		expect(resultBit1).isEqualTo(1);
		expect(resultBit2).isEqualTo(1);
		expect(resultBit3).isEqualTo(1);
		expect(resultBit4).isEqualTo(0);
		expect(resultBit5).isEqualTo(0);
		expect(resultBit6).isEqualTo(0);
		expect(resultBit7).isEqualTo(0);
		expect(resultBit8).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_getBitAtAsInt_4() {
		
		//setup
		final var testUnit = new ByteWrapper(1, 1, 1, 1, 1, 1, 1, 1);
		
		//execution
		final var resultBit1 = testUnit.getBitAtAsInt(1);
		final var resultBit2 = testUnit.getBitAtAsInt(2);
		final var resultBit3 = testUnit.getBitAtAsInt(3);
		final var resultBit4 = testUnit.getBitAtAsInt(4);
		final var resultBit5 = testUnit.getBitAtAsInt(5);
		final var resultBit6 = testUnit.getBitAtAsInt(6);
		final var resultBit7 = testUnit.getBitAtAsInt(7);
		final var resultBit8 = testUnit.getBitAtAsInt(8);
		
		//verification
		expect(resultBit1).isEqualTo(1);
		expect(resultBit2).isEqualTo(1);
		expect(resultBit3).isEqualTo(1);
		expect(resultBit4).isEqualTo(1);
		expect(resultBit5).isEqualTo(1);
		expect(resultBit6).isEqualTo(1);
		expect(resultBit7).isEqualTo(1);
		expect(resultBit8).isEqualTo(1);
	}
	
	//method
	@TestCase
	public void testCase_toBitString() {
		
		//setup
		final var testUnit = new ByteWrapper(0, 0, 0, 0, 0, 0, 0, 0);
		
		//execution
		final var result = testUnit.toBitString();
		
		//verification
		expect(result).isEqualTo("00000000");
	}
	
	//method
	@TestCase
	public void testCase_toBitString_2() {
		
		//setup
		final var testUnit = new ByteWrapper(0, 0, 0, 0, 0, 1, 1, 1);
		
		//execution
		final var result = testUnit.toBitString();
		
		//verification
		expect(result).isEqualTo("00000111");
	}
	
	//method
	@TestCase
	public void testCase_toBitString_3() {
		
		//setup
		final var testUnit = new ByteWrapper(1, 1, 1, 0, 0, 0, 0, 0);
		
		//execution
		final var result = testUnit.toBitString();
		
		//verification
		expect(result).isEqualTo("11100000");
	}
	
	//method
	@TestCase
	public void testCase_toBitString_4() {
		
		//setup
		final var testUnit = new ByteWrapper(1, 1, 1, 1, 1, 1, 1, 1);
		
		//execution
		final var result = testUnit.toBitString();
		
		//verification
		expect(result).isEqualTo("11111111");
	}
	
	//method
	@TestCase
	public void testCase_toByte() {
		for (var i = 0; i <= 255; i++) {
			
			//setup
			final var testUnit = ByteWrapper.fromNumber(i);
			
			//execution
			final var resullt = testUnit.toByte();
			
			//verification
			expect(resullt).isEqualTo(i - 128);
		}
	}
	
	//method
	@TestCase
	public void testCase_toInt() {
		
		//setup
		final var testUnit = new ByteWrapper(0, 0, 0, 0, 0, 0, 0, 0);
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_toInt_2() {
		
		//setup
		final var testUnit = new ByteWrapper(0, 0, 0, 0, 0, 1, 1, 1);
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(7);
	}
	
	//method
	@TestCase
	public void testCase_toInt_3() {
		
		//setup
		final var testUnit = new ByteWrapper(1, 1, 1, 0, 0, 0, 0, 0);
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(224);
	}
	
	//method
	@TestCase
	public void testCase_toInt_4() {
		
		//setup
		final var testUnit = new ByteWrapper(1, 1, 1, 1, 1, 1, 1, 1);
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(255);
	}
}
