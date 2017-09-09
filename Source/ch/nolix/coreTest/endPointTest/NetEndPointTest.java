//package declaration
package ch.nolix.coreTest.endPointTest;

//own imports
import ch.nolix.core.endPoint.EndPoint;
import ch.nolix.core.endPoint.IEndPointTaker;
import ch.nolix.core.endPoint.NetServer;
import ch.nolix.core.endPoint.NetEndPoint;

//own import
import ch.nolix.core.test2.Test;

//test class
/**
 * This class is a test class for the net end point class.
 * A net end point test is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 50
 */
public final class NetEndPointTest extends Test {

	//test method
	public void test_send() {
		
		//test parameter
		final int port = 29000;
		final String message = "Hello World!";
		
		//setup	
			final ReceiverMock receiverMock = new ReceiverMock();
		
			final NetServer netServer
			= new NetServer(
				port,
				new IEndPointTaker() {public void takeEndPoint(EndPoint endPoint) {endPoint.setReceiver(receiverMock);}}	
			);
			
			final NetEndPoint netEndPoint = new NetEndPoint(port);
			
		//execution			
			netEndPoint.send(message);
			
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException exception) {}
		
		//verification
		expectThat(receiverMock.getLastReceivedMessage()).equals(message);
		
		//cleanup
		netServer.close();
		netEndPoint.close();
	}
}
