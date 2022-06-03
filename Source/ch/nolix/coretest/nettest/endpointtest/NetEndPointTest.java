//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//own imports
import ch.nolix.core.environment.nolixenvironment.NolixEnvironment;
import ch.nolix.core.net.endpoint.EndPoint;
import ch.nolix.core.net.endpoint.IEndPointTaker;
import ch.nolix.core.net.endpoint.NetEndPoint;
import ch.nolix.core.net.endpoint.Server;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
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
		try (final var netServer = new Server(port)) {
			
			//setup
			netServer.addDefaultEndPointTaker(new TestEndPointTaker());
			
			//execution & verification
			expectRunning(
				() -> {
					try (final var result = new NetEndPoint(port)) {
						GlobalSequencer.waitForMilliseconds(
							NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS
						);
					}
				}
			)
			.doesNotThrowException();
		}
	}
	
	//method
	@TestCase
	public void testCase_send() {
		
		//parameter definition
		final var port = 50000;
		
		try (final var netServer = new Server(port)) {
			
			//setup
			final var endPointTaker = new TestEndPointTaker();
			netServer.addDefaultEndPointTaker(endPointTaker);
			
			try (final var testUnit = new NetEndPoint(port)) {
				
				//execution
				testUnit.send("MESSAGE");
				GlobalSequencer.waitForMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS);
				
				//verification
				expect(endPointTaker.getReceivedMessage()).isEqualTo("MESSAGE");
			}
		}
	}
}
