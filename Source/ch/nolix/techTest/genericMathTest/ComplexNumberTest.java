//package declaration
package ch.nolix.techTest.genericMathTest;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.core.test.Test;
import ch.nolix.tech.genericMath.ComplexNumber;

//test class
public final class ComplexNumberTest extends Test {
	
	//test case
	public void test_getMangitude() {
		
		//execution & verification
		expect(new ComplexNumber(0.0, 0.0, 10).getMagnitude()).isEqualTo(new BigDecimal(0.0).setScale(10));
		expect(new ComplexNumber(0.0, 1.0, 10).getMagnitude()).isEqualTo(new BigDecimal(1.0).setScale(10));
		expect(new ComplexNumber(1.0, 0.0, 10).getMagnitude()).isEqualTo(new BigDecimal(1.0).setScale(10));
		expect(new ComplexNumber(3.0, 4.0, 10).getMagnitude()).isEqualTo(new BigDecimal(5.0).setScale(10));
		expect(new ComplexNumber(4.0, 3.0, 10).getMagnitude()).isEqualTo(new BigDecimal(5.0).setScale(10));
	}
	
	//test case
	public void test_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary() {
		
		//execution & verification
		expectNot(new ComplexNumber(1.0, -1.0).isPureImaginary());
		expectNot(new ComplexNumber(1.0, 0.0).isPureImaginary());
		expectNot(new ComplexNumber(1.0, 1.0).isPureImaginary());
	}
	
	//test case
	public void test_isPureReal_whenTheGivenComplexNumberIsNotPureReal() {
		
		//execution & verification
		expectNot(new ComplexNumber(-1.0, 1.0).isPureReal());
		expectNot(new ComplexNumber(0.0, 1.0).isPureReal());
		expectNot(new ComplexNumber(1.0, 1.0).isPureReal());
	}
	
	//test case
	public void test_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary() {
		
		//execution & verification
		expect(new ComplexNumber(0.0, -1.0).isPureImaginary());
		expect(new ComplexNumber(0.0, 1.0).isPureImaginary());
	}
	
	//test case
	public void test_isPureReal_whenTheGivenComplexNumberIsPureReal() {
		
		//execution & verification
		expect(new ComplexNumber(-1.0, 0.0).isPureReal());
		expect(new ComplexNumber(0.0, 0.0).isPureReal());
		expect(new ComplexNumber(1.0, 0.0).isPureReal());
	}
}
