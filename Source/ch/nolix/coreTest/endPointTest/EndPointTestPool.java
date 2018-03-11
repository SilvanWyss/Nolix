//package declaration
package ch.nolix.coreTest.endPointTest;

import ch.nolix.primitive.testoid.TestPool;

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
	 * Creates new end point test pool.
	 */
	public EndPointTestPool() {
		addTest(
			new NetServerTest(),
			new NetEndPointTest()
		);
	}
}
