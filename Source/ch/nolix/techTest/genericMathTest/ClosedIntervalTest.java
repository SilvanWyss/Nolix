//package declaration
package ch.nolix.techTest.genericMathTest;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.test.Test;
import ch.nolix.tech.genericMath.ClosedInterval;

//test class
public final class ClosedIntervalTest extends Test {
	
	//test case
	public void testCase_creation_whenTheGivenMinIsNull() {
		expect(() -> new ClosedInterval(null, BigDecimal.valueOf(1.0)))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given minimum is null.");
	}
	
	//test case
	public void testCase_creation_whenTheGivenMaxIsNull() {
		expect(() -> new ClosedInterval(BigDecimal.valueOf(1.0), null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given maximum is null.");
	}
	
	//test case
	public void testCase_getLength() {
		
		//execution & verification
		expect(new ClosedInterval(-1.0, -1.0, 10).getLength()).isEqualTo(BigDecimal.valueOf(0.0).setScale(10));
		expect(new ClosedInterval(-1.0, 0.0, 10).getLength()).isEqualTo(BigDecimal.valueOf(1.0).setScale(10));
		expect(new ClosedInterval(-1.0, 1.0, 10).getLength()).isEqualTo(BigDecimal.valueOf(2.0).setScale(10));
	}
	
	//test case
	public void testCase_toString() {
		
		//execution & verification
		expect(new ClosedInterval(-1.0, 1.0, 5).toString()).isEqualTo("[-1.00000, 1.00000]");
	}
}
