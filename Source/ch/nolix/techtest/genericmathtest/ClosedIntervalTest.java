//package declaration
package ch.nolix.techtest.genericmathtest;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.test.Test;
import ch.nolix.tech.genericMath.ClosedInterval;

//class
public final class ClosedIntervalTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation_whenTheGivenMinIsNull() {
		expect(() -> new ClosedInterval(null, BigDecimal.valueOf(1.0)))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given minimum is null.");
	}
	
	//method
	@TestCase
	public void testCase_creation_whenTheGivenMaxIsNull() {
		expect(() -> new ClosedInterval(BigDecimal.valueOf(1.0), null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given maximum is null.");
	}
		
	//method
	@TestCase
	public void testCase_getLength_1() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, -1.0, scale);
		
		//execution
		final var result = testUnit.getLength();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getLength_2() {

		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, 0.0, scale);
		
		//execution
		final var result = testUnit.getLength();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getLength_3() {

		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, 1.0, scale);
		
		//execution
		final var result = testUnit.getLength();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(2.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, 1.0, 5);
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("[-1.00000, 1.00000]");
	}
}
