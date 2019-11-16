//package declaration
package ch.nolix.elementTest.timeTest;

import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class TimeTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new core test pool.
	 */
	public TimeTestPool() {
		addTestClass(TimeTest.class);
	}
}
