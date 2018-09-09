//package declaration
package ch.nolix.coreTest.enumTest;

import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public final class EnumTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new enum test pool.
	 */
	public EnumTestPool() {
		addTestClass(
			DirectionTest.class,
			UniDirectionTest.class
		);
	}
}
