//package declaration
package ch.nolix.commontest.nettest.endpointtest;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
public final class EndPointTestPool extends TestPool {
	
	//constructor
	public EndPointTestPool() {
		super(NetEndPointTest.class, NetServerTest.class);
	}
}
