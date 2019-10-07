//package declaration
package ch.nolix.commonTest.endPoint2Test;

//own imports
import ch.nolix.common.endPoint2.EndPoint;
import ch.nolix.common.endPoint2.IEndPointTaker;
import ch.nolix.common.endPoint2.NetEndPoint;
import ch.nolix.common.endPoint2.NetServer;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.test.Test;

//test class
public final class NetEndPointTest extends Test {
	
	//static class
	private static class EndPointTakerMock implements IEndPointTaker{
		
		//optional attribute
		private String receivedMessage;

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
			endPoint.setReceiver(m -> setMessage(m));
		}
		
		//method
		private void setMessage(final String receivedMessage) {			
			this.receivedMessage = receivedMessage;
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
	public void testCase_send() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		final var endPointTakerMock = new EndPointTakerMock();
		netServer.addMainEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		
		//execution
		netEndPoint.send("MESSAGE");
		Sequencer.waitForMilliseconds(200);
		
		//verification
		expect(endPointTakerMock.getReceivedMessageOrNull()).isEqualTo("MESSAGE");
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
}
