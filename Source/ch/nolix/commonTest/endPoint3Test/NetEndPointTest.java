//package declaration
package ch.nolix.commonTest.endPoint3Test;

//own imports
import ch.nolix.common.endPoint3.EndPoint;
import ch.nolix.common.endPoint3.IEndPointTaker;
import ch.nolix.common.endPoint3.NetEndPoint;
import ch.nolix.common.endPoint3.NetServer;
import ch.nolix.common.test.Test;

//test class
/**
 * A {@link NetEndPointTest} is a test for {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 90
 */
public final class NetEndPointTest extends Test {
	
	//mock class
	private static class EndPointTakerMock implements IEndPointTaker {
		
		//optional attribute
		private String receivedMessage;
		
		//method
		@Override
		public String getName() {
			return "EndPointTaker";
		}
		
		//method
		public String getReceivedMessageOrNull() {
			return receivedMessage;
		}
		
		//method
		@Override
		public void takeEndPoint(final EndPoint endPoint) {
			endPoint.setReplier(m -> getReply(m));
		}
		
		//method
		private String getReply(final String message) {
			receivedMessage = message;
			return "REPLY";
		}
	}
	
	//test case
	public void testCase_creation() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		netServer.addMainEndPointTaker(new EndPointTakerMock());
		
		//execution & verification
		expect(() -> new NetEndPoint(port).close()).doesNotThrowException();
		
		//cleanup
		netServer.close();
	}
	
	//test case
	public void testCase_send() throws InterruptedException {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		final var endPointTakerMock = new EndPointTakerMock();
		netServer.addMainEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		
		//execution
		final var reply = netEndPoint.sendAndGetReply("MESSAGE");
		
		//verification
		expect(endPointTakerMock.getReceivedMessageOrNull()).isEqualTo("MESSAGE");
		expect(reply).isEqualTo("REPLY");
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
}
