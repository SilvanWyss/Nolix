//package declaration
package ch.nolix.commonTest.mathematicsTest;

//own imports
import ch.nolix.common.invalidArgumentException.ZeroArgumentException;
import ch.nolix.common.mathematics.QuadraticFunction;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the quadratic function class.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 40
 */
public final class QuadraticFunctionTest extends ZetaTest {

	//test method
	public void test_constructor() {
		
		//execution and verification
		expectThat(() -> new QuadraticFunction(0.0, 0.0, 0.0))
		.throwsExceptionOfType(ZeroArgumentException.class);
	}
	
	//test method
	public void test_hasMin_1() {
		
		//setup
		final QuadraticFunction quadraticFunction = new QuadraticFunction(1.0, 0.0, 0.0);
		
		//execution and verification
		expectThat(quadraticFunction.hasMin());
	}
	
	//test method
	public void test_hasMin_2() {
		
		//setup
		final QuadraticFunction quadraticFunction = new QuadraticFunction(-1.0, 0.0, 0.0);
		
		//execution and verification
		expectThatNot(quadraticFunction.hasMin());
	}
}
