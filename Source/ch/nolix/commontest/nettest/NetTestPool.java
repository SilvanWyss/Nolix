//package declaration
package ch.nolix.commontest.nettest;

import ch.nolix.common.testing.basetest.TestPool;

//class
public final class NetTestPool extends TestPool {
	
	//constructor
	public NetTestPool() {
		super(
			new ch.nolix.commontest.nettest.endpointtest.EndPointTestPool(),
			new ch.nolix.commontest.nettest.endpoint2test.EndPointTestPool(),
			new ch.nolix.commontest.nettest.endpoint3test.EndPointTestPool()
		);
	}
}
