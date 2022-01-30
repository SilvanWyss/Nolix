//package declaration
package ch.nolix.commontest.nettest.endpoint2test;

import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan
 * @date 2017-03-01
 * @lines 20
 */
public final class EndPointTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link EndPointTestPool}.
	 */
	public EndPointTestPool() {
		super(NetEndPointTest.class);
	}
}
