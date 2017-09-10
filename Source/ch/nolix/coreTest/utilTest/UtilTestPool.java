//package declaration
package ch.nolix.coreTest.utilTest;

import ch.nolix.core.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class UtilTestPool extends TestPool {

	//constructor
	/**
	 * Creates new util test pool.
	 */
	public UtilTestPool() {
		addTest(new TimeTest());
	}
}
