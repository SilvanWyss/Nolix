/*
 * file:	MathematicsTestPool.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	20
 */

//package declaration
package ch.nolix.commonTest.mathematicsTest;

//own import
import ch.nolix.common.test.TestPool;

//class
public final class MathematicsTestPool extends TestPool {

	//constructor
	/**
	 * Creates new mathematics test pool.
	 */
	public MathematicsTestPool() {
		addTest(
			new ARModelTest(),
			new CalculatorTest(),
			new MatrixTest(),
			new PolynomTest()
		);
	}
}
