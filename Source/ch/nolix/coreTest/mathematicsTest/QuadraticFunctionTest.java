//package declaration
package ch.nolix.coreTest.mathematicsTest;

//own imports
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.mathematics.QuadraticFunction;
import ch.nolix.core.test2.Test;

//test class
/**
 * A quadratic function test is a test for the quadratic function class.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 40
 */
public final class QuadraticFunctionTest extends Test {

	//test method
	public void test_constructor() {
		
		//execution and verification
		expect(() -> new QuadraticFunction(0.0, 0.0, 0.0))
		.throwsExceptionOfType(InvalidArgumentException.class);
	}
	
	//test method
	public void test_hasMin() {
		
		//setup
		final QuadraticFunction quadraticFunction =
		new QuadraticFunction(1.0, 0.0, 0.0);
		
		//execution and verification
		expect(quadraticFunction.hasMin());
	}
	
	//test method
	public void test_hasMin_2() {
		
		//setup
		final QuadraticFunction quadraticFunction =
		new QuadraticFunction(-1.0, 0.0, 0.0);
		
		//execution and verification
		expectNot(quadraticFunction.hasMin());
	}
}
