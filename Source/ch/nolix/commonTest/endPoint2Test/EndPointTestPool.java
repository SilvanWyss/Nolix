//package declaration
package ch.nolix.commonTest.endPoint2Test;

import ch.nolix.common.basetest.TestPool;

//class
public final class EndPointTestPool extends TestPool {
	
	//constructor
	public EndPointTestPool() {
		super(NetEndPointTest.class, NetServerTest.class);
	}
}
