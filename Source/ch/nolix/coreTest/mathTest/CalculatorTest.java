//package declaration
package ch.nolix.coreTest.mathTest;

//own import
import ch.nolix.core.math.Calculator;
import ch.nolix.core.test.Test;

//test class
/**
 * A {@link CalculatorTest} is a {@link Test} for the {@link Calculator}.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public final class CalculatorTest extends Test {
	
	//test case
	public void testCase_getMax() {
		
		//execution
		final var min = Calculator.getMax(-2.0, -1.0, 0.0, 1.0, 2.0);
		
		//validation
		expect(min).isEqualTo(2.0);
	}
	
	//test case
	public void testCase_getMin() {
		
		//execution
		final var min = Calculator.getMin(-2.0, -1.0, 0.0, 1.0, 2.0);
		
		//validation
		expect(min).isEqualTo(-2.0);
	}
}
