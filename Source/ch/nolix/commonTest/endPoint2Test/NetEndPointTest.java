//package declaration
package ch.nolix.commonTest.endPoint2Test;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.endPoint2.EndPoint;
import ch.nolix.common.endPoint2.IEndPointTaker;
import ch.nolix.common.endPoint2.NetEndPoint;
import ch.nolix.common.endPoint2.NetServer;
import ch.nolix.common.nolixEnvironment.NolixEnvironment;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.test.Test;

//class
public final class NetEndPointTest extends Test {
	
	//static class
	private static final class TestEndPointTaker implements IEndPointTaker {
		
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
			endPoint.setReceiver(this::setMessage);
		}
		
		//method
		private void setMessage(final String receivedMessage) {			
			this.receivedMessage = receivedMessage;
		}
	}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		netServer.addMainEndPointTaker(new TestEndPointTaker());
		
		//execution & verification
		expect(
			() -> {
				final var netEndPoint = new NetEndPoint(port);
				Sequencer.waitForMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS);
				netEndPoint.close();
			}
		)
		.doesNotThrowException();
		
		//cleanup
		netServer.close();
	}
	
	//method
	@TestCase
	public void testCase_send() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		final var endPointTakerMock = new TestEndPointTaker();
		netServer.addMainEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		
		//execution
		netEndPoint.send("MESSAGE");
		Sequencer.waitForMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS);
		
		//verification
		expect(endPointTakerMock.getReceivedMessageOrNull()).isEqualTo("MESSAGE");
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
}
