//package declaration
package ch.nolix.coreTest.mathematicsTest;

import ch.nolix.primitive.testoid.TestPool;

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
		addTest(
			new ARModelTest(),
			new CalculatorTest(),
			new MatrixTest(),
			new PolynomTest(),
			new QuadraticFunctionTest()
		);
	}
}
