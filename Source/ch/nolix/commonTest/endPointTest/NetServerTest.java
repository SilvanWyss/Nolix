//package declaration
package ch.nolix.commonTest.endPointTest;

import ch.nolix.common.endPoint.NetServer;
import ch.nolix.common.test.Test;

//test class
/**
 * This class is a test class for the net server test class.
 * A net server test is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public final class NetServerTest extends Test {

	//test case
	public void testCase_abort() {
		
		//test parameter
		final int port = 27900;
		
		//execution
		
			final NetServer netServer
			= new NetServer(port, ep -> {});
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
			netServer.close();
			
			final NetServer netServer2
			= new NetServer(port, ep -> {});
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
			netServer2.close();
				
		//verification
		expect(netServer.isClosed());
		expect(netServer2.isClosed());
	}
}
