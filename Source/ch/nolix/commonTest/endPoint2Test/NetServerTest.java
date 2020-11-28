//package declaration
package ch.nolix.commonTest.endPoint2Test;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.endpoint2.NetServer;
import ch.nolix.common.test.Test;

//class
public final class NetServerTest extends Test {
	
	//method
	@TestCase
	public void testCase_close() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		
		//setup verification
		expect(netServer.getPort()).isEqualTo(port);
		expect(netServer.isOpen());
		
		//execution & verification
		expect(netServer::close).doesNotThrowException();
		
		//verification
		expect(netServer.isClosed());
	}
}
