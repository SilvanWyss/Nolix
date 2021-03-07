//package declaration
package ch.nolix.commontest.mathtest;

import ch.nolix.common.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 * @lines 20
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
