//package declaration
package ch.nolix.commontest.nettest.endpointtest;

import ch.nolix.core.environment.nolixenvironment.NolixEnvironment;
import ch.nolix.core.net.endpoint.EndPoint;
import ch.nolix.core.net.endpoint.IEndPointTaker;
import ch.nolix.core.net.endpoint.NetEndPoint;
import ch.nolix.core.net.endpoint.Server;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class NetEndPointTest extends Test {
	
	//static class
	private static final class TestEndPointTaker implements IEndPointTaker {
		
		//optional attribute
		private String receivedMessage;
		
		//method
		@Override
		public String getName() {
			return "EndPointTaker";
		}
		
		//method
		public String getReceivedMessage() {
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
		
		//parameter definition
		final var port = 50000;
		
		//setup
		final var netServer = new Server(port);
		netServer.addDefaultEndPointTaker(new TestEndPointTaker());
		
		//execution & verification
		expectRunning(
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
		
		//parameter definition
		final var port = 50000;
		
		//setup
		final var netServer = new Server(port);
		final var endPointTakerMock = new TestEndPointTaker();
		netServer.addDefaultEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		
		//execution
		netEndPoint.send("MESSAGE");
		Sequencer.waitForMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS);
		
		//verification
		expect(endPointTakerMock.getReceivedMessage()).isEqualTo("MESSAGE");
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
}
