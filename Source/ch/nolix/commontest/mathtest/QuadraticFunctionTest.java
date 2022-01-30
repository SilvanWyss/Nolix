//package declaration
package ch.nolix.commontest.mathtest;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.math.QuadraticFunction;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
/**
 * A quadratic function test is a test for the quadratic function class.
 * 
 * @author Silvan Wyss
 * @date 2017-04-14
 * @lines 40
 */
public final class QuadraticFunctionTest extends Test {

	//method
	@TestCase
	public void testCase_creation() {
		
		//execution & verification
		expectRunning(() -> new QuadraticFunction(0.0, 0.0, 0.0))
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_hasMin() {
		
		//setup
		final QuadraticFunction quadraticFunction =
		new QuadraticFunction(1.0, 0.0, 0.0);
		
		//execution & verification
		expect(quadraticFunction.hasMin());
	}
	
	//method
	@TestCase
	public void testCase_hasMin_2() {
		
		//setup
		final QuadraticFunction quadraticFunction =
		new QuadraticFunction(-1.0, 0.0, 0.0);
		
		//execution & verification
		expectNot(quadraticFunction.hasMin());
	}
}
