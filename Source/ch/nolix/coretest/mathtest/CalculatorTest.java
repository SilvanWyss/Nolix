//package declaration
package ch.nolix.coretest.mathtest;

//own imports
import ch.nolix.core.math.Calculator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
/**
 * A {@link CalculatorTest} is a {@link Test} for the {@link Calculator}.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class CalculatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_getMax() {
		
		//execution
		final var min = Calculator.getMax(-2.0, -1.0, 0.0, 1.0, 2.0);
		
		//validation
		expect(min).isEqualTo(2.0);
	}
	
	//method
	@TestCase
	public void testCase_getMin() {
		
		//execution
		final var min = Calculator.getMin(-2.0, -1.0, 0.0, 1.0, 2.0);
		
		//validation
		expect(min).isEqualTo(-2.0);
	}
}
