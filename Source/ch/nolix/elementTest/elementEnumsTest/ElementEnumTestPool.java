//package declaration
package ch.nolix.elementTest.elementEnumsTest;

import ch.nolix.core.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public final class ElementEnumTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new enum test pool.
	 */
	public ElementEnumTestPool() {
		addTestClass(
			DirectionTest.class,
			UniDirectionTest.class
		);
	}
}
