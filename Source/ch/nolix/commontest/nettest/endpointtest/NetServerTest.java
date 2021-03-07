//package declaration
package ch.nolix.commontest.nettest.endpointtest;

import ch.nolix.common.net.endpoint.NetServer;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

//class
public final class NetServerTest extends Test {
	
	//method
	@TestCase
	public void testCase_close() {
		
		//parameter definition
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		
		//setup verification
		expect(netServer.getPort()).isEqualTo(port);
		expect(netServer.isOpen());
		
		//execution & verification
		expectRunning(netServer::close).doesNotThrowException();
		
		//verification
		expect(netServer.isClosed());
	}
}
