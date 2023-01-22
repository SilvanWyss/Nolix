//package declaration
package ch.nolix.businesstest.mathtest.bigdecimalmathtest;

//Java imports
import java.math.BigDecimal;

import ch.nolix.business.math.bigdecimalmath.ComplexNumber;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class ComplexNumberTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation_1A() {
		
		//execution
		final var result = new ComplexNumber(0.0, 0.0);
		
		//verification
		expect(result.getRealComponent().doubleValue()).isEqualTo(0.0);
		expect(result.getImaginaryComponent().doubleValue()).isEqualTo(0.0);
	}
	
	//method
	@TestCase
	public void testCase_creation_1B() {
		
		//execution
		final var result = new ComplexNumber(1.0, 0.0);
		
		//verification
		expect(result.getRealComponent().doubleValue()).isEqualTo(1.0);
		expect(result.getImaginaryComponent().doubleValue()).isEqualTo(0.0);
	}
	
	//method
	@TestCase
	public void testCase_creation_1C() {
		
		//execution
		final var result = new ComplexNumber(0.0, 1.0);
		
		//verification
		expect(result.getRealComponent().doubleValue()).isEqualTo(0.0);
		expect(result.getImaginaryComponent().doubleValue()).isEqualTo(1.0);
	}
	
	//method
	@TestCase
	public void testCase_getConjugate_1A() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0, 0.0);
		
		//execution
		final var result = testUnit.getConjugate();
		
		//verification
		expect(result).isEqualTo(new ComplexNumber(0.0, 0.0));
	}
	
	//method
	@TestCase
	public void testCase_getConjugate_1B() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0, 1.0);
		
		//execution
		final var result = testUnit.getConjugate();
		
		//verification
		expect(result).isEqualTo(new ComplexNumber(0.0, -1.0));
	}
	
	//method
	@TestCase
	public void testCase_getConjugate_1C() {
		
		//setup
		final var testUnit = new ComplexNumber(1.0, 0.0);
		
		//execution
		final var result = testUnit.getConjugate();
		
		//verification
		expect(result).isEqualTo(new ComplexNumber(1.0, 0.0));
	}
	
	//method
	@TestCase
	public void testCase_getConjugate_1D() {
		
		//setup
		final var testUnit = new ComplexNumber(1.0, 1.0);
		
		//execution
		final var result = testUnit.getConjugate();
		
		//verification
		expect(result).isEqualTo(new ComplexNumber(1.0, -1.0));
	}
	
	//method
	@TestCase
	public void testCase_getMagnitude_1A() {
		
		//parameter definition
		final var scale = 10;
		
		//setup
		final var testUnit = new ComplexNumber(0.0, 0.0);
		
		//execution
		final var result = testUnit.getMagnitude();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
	}
		
	//method
	@TestCase
	public void testCase_getMagnitude_1B() {
		
		//parameter definition
		final var scale = 10;
		
		//setup
		final var testUnit = new ComplexNumber(0.0, 1.0);
		
		//execution
		final var result = testUnit.getMagnitude();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getMagnitude_1C() {
		
		//parameter definition
		final var scale = 10;
		
		//setup
		final var testUnit = new ComplexNumber(1.0, 0.0);
		
		//execution
		final var result = testUnit.getMagnitude();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getMagnitude_2A() {
		
		//parameter definition
		final var scale = 10;
		
		//setup
		final var testUnit = new ComplexNumber(3.0, 4.0);
		
		//execution
		final var result = testUnit.getMagnitude();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(5.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getMagnitude_2B() {
		
		//parameter definition
		final var scale = 10;
		
		//setup
		final var testUnit = new ComplexNumber(4.0, 3.0);
		
		//execution
		final var result = testUnit.getMagnitude();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(5.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getProduct_1A() {
		
		//parameter definition
		final var factor = new ComplexNumber(0.0, 0.0);
		
		//setup
		final var testUnit = new ComplexNumber(0.0, 0.0);
				
		//execution
		final var result = testUnit.getProduct(factor);
		
		//verification
		expect(result).isEqualTo(new ComplexNumber(0.0, 0.0));	
	}
	
	//method
	@TestCase
	public void testCase_getProduct_1B() {
		
		//parameter definition
		final var factor = new ComplexNumber(0.0, 0.0);
		
		//setup
		final var testUnit = new ComplexNumber(1.0, 1.0);
		
		//execution
		final var result = testUnit.getProduct(factor);
		
		//verification
		expect(result).isEqualTo(new ComplexNumber(0.0, 0.0));	
	}
	
	//method
	@TestCase
	public void testCase_getSum_1A() {
		
		//setup
		final var testUnit = new ComplexNumber(-1.0, -1.0);
		
		//execution & verification
		expect(testUnit.getSum(new ComplexNumber(-1.0, -1.0))).isEqualTo(new ComplexNumber(-2.0, -2.0));
		expect(testUnit.getSum(new ComplexNumber(-1.0, 0.0))).isEqualTo(new ComplexNumber(-2.0, -1.0));
		expect(testUnit.getSum(new ComplexNumber(-1.0, 1.0))).isEqualTo(new ComplexNumber(-2.0, 0.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, -1.0))).isEqualTo(new ComplexNumber(-1.0, -2.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, 0.0))).isEqualTo(new ComplexNumber(-1.0, -1.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, 1.0))).isEqualTo(new ComplexNumber(-1.0, 0.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, -1.0))).isEqualTo(new ComplexNumber(0.0, -2.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, 0.0))).isEqualTo(new ComplexNumber(0.0, -1.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, 1.0))).isEqualTo(new ComplexNumber(0.0, 0.0));
	}
	
	//method
	@TestCase
	public void testCase_getSum_1B() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0, 0.0);
		
		//execution & verification
		expect(testUnit.getSum(new ComplexNumber(-1.0, -1.0))).isEqualTo(new ComplexNumber(-1.0, -1.0));
		expect(testUnit.getSum(new ComplexNumber(-1.0, 0.0))).isEqualTo(new ComplexNumber(-1.0, 0.0));
		expect(testUnit.getSum(new ComplexNumber(-1.0, 1.0))).isEqualTo(new ComplexNumber(-1.0, 1.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, -1.0))).isEqualTo(new ComplexNumber(0.0, -1.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, 0.0))).isEqualTo(new ComplexNumber(0.0, 0.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, 1.0))).isEqualTo(new ComplexNumber(0.0, 1.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, -1.0))).isEqualTo(new ComplexNumber(1.0, -1.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, 0.0))).isEqualTo(new ComplexNumber(1.0, 0.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, 1.0))).isEqualTo(new ComplexNumber(1.0, 1.0));
	}
	
	//method
	@TestCase
	public void testCase_getSum_1C() {
		
		//setup
		final var testUnit = new ComplexNumber(1.0, 1.0);
		
		//execution & verification
		expect(testUnit.getSum(new ComplexNumber(-1.0, -1.0))).isEqualTo(new ComplexNumber(0.0, 0.0));
		expect(testUnit.getSum(new ComplexNumber(-1.0, 0.0))).isEqualTo(new ComplexNumber(0.0, 1.0));
		expect(testUnit.getSum(new ComplexNumber(-1.0, 1.0))).isEqualTo(new ComplexNumber(0.0, 2.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, -1.0))).isEqualTo(new ComplexNumber(1.0, 0.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, 0.0))).isEqualTo(new ComplexNumber(1.0, 1.0));
		expect(testUnit.getSum(new ComplexNumber(0.0, 1.0))).isEqualTo(new ComplexNumber(1.0, 2.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, -1.0))).isEqualTo(new ComplexNumber(2.0, 0.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, 0.0))).isEqualTo(new ComplexNumber(2.0, 1.0));
		expect(testUnit.getSum(new ComplexNumber(1.0, 1.0))).isEqualTo(new ComplexNumber(2.0, 2.0));
	}
	
	//method
	@TestCase
	public void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_1() {
		
		//setup
		final var testUnit = new ComplexNumber(1.0, -1.0);
		
		//execution
		final var result = testUnit.isPureImaginary();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_2() {
		
		//setup
		final var testUnit = new ComplexNumber(1.0, 0.0);
		
		//execution
		final var result = testUnit.isPureImaginary();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_3() {
		
		//setup
		final var testUnit = new ComplexNumber(1.0, 1.0);
		
		//execution
		final var result = testUnit.isPureImaginary();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_1() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0, -1.0);
		
		//execution
		final var result = testUnit.isPureImaginary();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_2() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0, 0.0);
		
		//execution
		final var result = testUnit.isPureImaginary();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_3() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0, 1.0);
		
		//execution
		final var result = testUnit.isPureImaginary();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_1() {
		
		//setup
		final var testUnit = new ComplexNumber(-1.0,  1.0);
		
		//execution
		final var result = testUnit.isPureReal();
		
		//verification
		expectNot(result);
	}
		
	//method
	@TestCase
	public void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_2() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0,  1.0);
		
		//execution
		final var result = testUnit.isPureReal();
		
		//verification
		expectNot(result);
	}
		
	//method
	@TestCase
	public void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_3() {
		
		//setup
		final var testUnit = new ComplexNumber(1.0,  1.0);
		
		//execution
		final var result = testUnit.isPureReal();
		
		//verification
		expectNot(result);
	}
		
	//method
	@TestCase
	public void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_1() {
		
		//setup
		final var testUnit = new ComplexNumber(-1.0,  0.0);
		
		//execution
		final var result = testUnit.isPureReal();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_2() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0,  0.0);
		
		//execution
		final var result = testUnit.isPureReal();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_3() {
		
		//setup
		final var testUnit = new ComplexNumber(1.0,  0.0);
		
		//execution
		final var result = testUnit.isPureReal();
		
		//verification
		expect(result);
	}
}