//package declaration
package ch.nolix.elementTest.coreTest;

import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class CoreTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new core test pool.
	 */
	public CoreTestPool() {
		addTest(new TimeTest());
	}
}
