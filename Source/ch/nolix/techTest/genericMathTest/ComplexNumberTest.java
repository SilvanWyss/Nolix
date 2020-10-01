//package declaration
package ch.nolix.techTest.genericMathTest;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.tech.genericMath.ComplexNumber;

//class
public final class ComplexNumberTest extends Test {
	
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
		final var testUnit = new ComplexNumber(0.0, -1.0);
		
		//execution
		final var result = testUnit.isPureImaginary();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_3() {
		
		//setup
		final var testUnit = new ComplexNumber(0.0, -1.0);
		
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
