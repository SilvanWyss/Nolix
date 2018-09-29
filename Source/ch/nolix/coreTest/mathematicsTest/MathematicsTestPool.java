//package declaration
package ch.nolix.coreTest.mathematicsTest;

import ch.nolix.core.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public final class MathematicsTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new mathematics test pool.
	 */
	public MathematicsTestPool() {
		addTestClass(
			ARModelTest.class,
			CalculatorTest.class,
			MatrixTest.class,
			PolynomTest.class,
			QuadraticFunctionTest.class
		);
	}
}
