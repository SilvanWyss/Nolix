//package declaration
package ch.nolix.coretest.nettest.endpointtest;

import ch.nolix.core.testing.basetest.TestPool;

//class
public final class EndPointTestPool extends TestPool {
	
	//constructor
	public EndPointTestPool() {
		super(NetEndPointTest.class, NetServerTest.class);
	}
}
