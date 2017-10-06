//package declaration
package ch.nolix.coreTest.enumTest;

import ch.nolix.core.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public final class EnumTestPool extends TestPool {

	//constructor
	/**
	 * Creates new enum test pool.
	 */
	public EnumTestPool() {
		addTest(
			new DirectionTest(),
			new UniDirectionTest()
		);
	}
}
