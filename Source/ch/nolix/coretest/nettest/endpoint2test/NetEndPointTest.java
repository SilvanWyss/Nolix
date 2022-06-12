//package declaration
package ch.nolix.coretest.nettest.endpoint2test;

//own imports
import ch.nolix.core.net.endpoint2.EndPoint;
import ch.nolix.core.net.endpoint2.IEndPointTaker;
import ch.nolix.core.net.endpoint2.NetEndPoint;
import ch.nolix.core.net.endpoint2.Server;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class NetEndPointTest extends Test {
	
	//static class
	private static final class EndPointTaker implements IEndPointTaker {
		
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
			endPoint.setReplier(this::getReply);
		}
		
		//method
		private String getReply(final String message) {
			receivedMessage = message;
			return "REPLY";
		}
	}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//parameter definition
		final var port = 50000;
		
		try (final var server = new Server(port)) {
			
			//setup
			server.addDefaultEndPointTaker(new EndPointTaker());
			
			//execution & verification
			expectRunning(
				() -> {
					try (final var result = new NetEndPoint(port)) {
						GlobalSequencer.waitForMilliseconds(500);
					}
				}
			)
			.doesNotThrowException();
		}
	}
	
	//method
	@TestCase
	public void testCase_sendAndGetReply() {
		
		//parameter definition
		final var port = 50000;
				
		try (final var server = new Server(port)) {
			
			//setup
			final var endPointTaker = new EndPointTaker();
			server.addDefaultEndPointTaker(endPointTaker);
			
			try (final var testUnit = new NetEndPoint(port)) {
			
				//execution
				final var result = testUnit.getReplyTo("MESSAGE");
				
				//verification
				expect(endPointTaker.getReceivedMessageOrNull()).isEqualTo("MESSAGE");
				expect(result).isEqualTo("REPLY");
			}
		}
	}
}
