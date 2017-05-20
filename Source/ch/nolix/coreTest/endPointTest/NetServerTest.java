//package declaration
package ch.nolix.coreTest.endPointTest;

//own imports
import ch.nolix.core.endPoint.EndPoint;
import ch.nolix.core.endPoint.IEndPointTaker;
import ch.nolix.core.endPoint.NetServer;
import ch.nolix.core.test2.Test;

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

	//test method
	public void test_abort() {
		
		//test parameter
		final int port = 27900;
		
		//execution
		
			final NetServer netServer
			= new NetServer(
				port,
				new IEndPointTaker() {public void takeEndPoint(EndPoint endPoint) {}}
			);
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
			netServer.abort();
			
			final NetServer netServer2
			= new NetServer(
				port,
				new IEndPointTaker() {public void takeEndPoint(EndPoint endPoint) {}}
			);
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
			netServer2.abort();
				
		//verification
		expectThat(netServer.isAborted());
		expectThat(netServer2.isAborted());
	}
}
