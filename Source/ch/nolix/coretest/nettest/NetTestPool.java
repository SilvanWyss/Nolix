//package declaration
package ch.nolix.coretest.nettest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class NetTestPool extends TestPool {
	
	//constructor
	public NetTestPool() {
		super(
			new ch.nolix.coretest.nettest.endpointtest.EndPointTestPool(),
			new ch.nolix.coretest.nettest.endpoint2test.EndPointTestPool(),
			new ch.nolix.coretest.nettest.endpoint3test.EndPointTestPool()
		);
	}
}
