//package declaration
package ch.nolix.commonTest.mathTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.math.QuadraticFunction;
import ch.nolix.common.test.Test;

//test class
/**
 * A quadratic function test is a test for the quadratic function class.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 40
 */
public final class QuadraticFunctionTest extends Test {

	//method
	@TestCase
	public void testCase_creation() {
		
		//execution & verification
		expect(() -> new QuadraticFunction(0.0, 0.0, 0.0))
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
