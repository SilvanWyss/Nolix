//package declaration
package ch.nolix.coreTest.utilTest;

import ch.nolix.elementTest.coreTest.TimeTest;
import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class UtilTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new util test pool.
	 */
	public UtilTestPool() {
		addTest(new TimeTest());
	}
}
