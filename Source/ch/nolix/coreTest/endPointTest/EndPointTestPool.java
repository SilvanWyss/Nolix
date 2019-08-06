//package declaration
package ch.nolix.coreTest.endPointTest;

import ch.nolix.core.baseTest.TestPool;

//class
/**
 * An end point test pool is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 */
public final class EndPointTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new end point test pool.
	 */
	public EndPointTestPool() {
		addTestClass(
			NetServerTest.class,
			NetEndPointTest.class
		);
	}
}
