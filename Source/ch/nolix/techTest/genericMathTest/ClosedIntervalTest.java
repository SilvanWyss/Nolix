//package declaration
package ch.nolix.techTest.genericMathTest;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.test.Test;
import ch.nolix.tech.genericMath.ClosedInterval;

//test class
public final class ClosedIntervalTest extends Test {
	
	//test case
	public void test_constructor_whenTheGivenMinIsNull() {
		expect(() -> new ClosedInterval(null, new BigDecimal(1.0)))
		.throwsException()
		.ofType(NullArgumentException.class)
		.withMessage("The given minimum is null.");
	}
	
	//test case
	public void test_constructor_whenTheGivenMaxIsNull() {
		expect(() -> new ClosedInterval(new BigDecimal(1.0), null))
		.throwsException()
		.ofType(NullArgumentException.class)
		.withMessage("The given maximum is null.");
	}
	
	//test case
	public void test_getLength() {
		
		//execution & verification
		expect(new ClosedInterval(-1.0, -1.0, 10).getLength()).isEqualTo(new BigDecimal(0.0).setScale(10));
		expect(new ClosedInterval(-1.0, 0.0, 10).getLength()).isEqualTo(new BigDecimal(1.0).setScale(10));
		expect(new ClosedInterval(-1.0, 1.0, 10).getLength()).isEqualTo(new BigDecimal(2.0).setScale(10));
	}
	
	//test case
	public void test_toString() {
		
		//execution & verification
		expect(new ClosedInterval(-1.0, 1.0, 5).toString()).isEqualTo("[-1.00000, 1.00000]");
	}
}
