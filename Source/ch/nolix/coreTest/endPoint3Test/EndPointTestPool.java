//package declaration
package ch.nolix.coreTest.endPoint3Test;

import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan
 * @month 2017-02
 * @lines 20
 */
public final class EndPointTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new end point test pool.
	 */
	public EndPointTestPool() {
		addTestClass(NetEndPointTest.class);
	}
}
