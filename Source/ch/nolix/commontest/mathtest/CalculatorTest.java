//package declaration
package ch.nolix.commontest.mathtest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.test.Test;

//class
/**
 * A {@link CalculatorTest} is a {@link Test} for the {@link Calculator}.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
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
