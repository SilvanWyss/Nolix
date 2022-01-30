//package declaration
package ch.nolix.commontest.nettest.endpointtest;

import ch.nolix.core.testing.basetest.TestPool;

//class
public final class EndPointTestPool extends TestPool {
	
	//constructor
	public EndPointTestPool() {
		super(NetEndPointTest.class, NetServerTest.class);
	}
}
