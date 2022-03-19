//package declaration
package ch.nolix.coretest.mathtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class MathTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link MathTestPool}.
	 */
	public MathTestPool() {
		super(
			ARModelTest.class,
			CalculatorTest.class,
			MatrixTest.class,
			PolynomTest.class,
			QuadraticFunctionTest.class
		);
	}
}
