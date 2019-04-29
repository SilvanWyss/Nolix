//package declaration
package ch.nolix.coreTest.endPoint2Test;

//own imports
import ch.nolix.core.endPoint2.NetServer;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.endPoint2.EndPoint;
import ch.nolix.core.endPoint2.IEndPointTaker;
import ch.nolix.core.endPoint2.NetEndPoint;
import ch.nolix.core.test.Test;

//test class
public final class NetEndPointTest extends Test {
	
	//test case
	public void testCase_creation() {
		
		//test parameters
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		registerToClose(netServer);
		netServer.addMainEndPointTaker(new EndPointTakerMock());
		
		//execution & verification
		expect(
			() -> {
				final var netEndPoint = new NetEndPoint(port);
				registerToClose(netEndPoint);
			}
		)
		.doesNotThrowException();
	}
	
	//test case
	public void testCase_creation_whenNetServerDoesNotContainAnEndPointTaker() {
		
		//test parameters
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		registerToClose(netServer);
		
		//execution & verification
		expect(
			() -> {
				final var netEndPoint = new NetEndPoint(port);
				registerToClose(netEndPoint);
			}
		)
		.throwsException();
	}
	
	//test case
	public void testCase_send() {
		
		//test parameters
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		registerToClose(netServer);
		final var endPointTakerMock = new EndPointTakerMock();
		netServer.addMainEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		registerToClose(netEndPoint);
		
		//execution
		netEndPoint.send("TEST MESSAGE");
		Sequencer.waitForMilliseconds(200);
		
		//verification
		expect(endPointTakerMock.getReceivedMessage()).isEqualTo("TEST MESSAGE");
	}
	
	//inner class
	private class EndPointTakerMock implements IEndPointTaker{
		
		//optional attribute
		private String receivedMessage;

		@Override
		public String getName() {
			return "EndPointTaker";
		}
		
		//method
		public String getReceivedMessage() {
			return receivedMessage;
		}

		@Override
		public void takeEndPoint(final EndPoint endPoint) {
			endPoint.setReceiver(m -> setMessage(m));
		}
		
		//method
		private void setMessage(final String receivedMessage) {			
			this.receivedMessage = receivedMessage;
		}
	}
}
