//package declaration
package ch.nolix.commontest.endpoint2test;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
public final class EndPointTestPool extends TestPool {
	
	//constructor
	public EndPointTestPool() {
		super(NetEndPointTest.class, NetServerTest.class);
	}
}
