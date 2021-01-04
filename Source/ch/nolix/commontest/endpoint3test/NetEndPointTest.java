//package declaration
package ch.nolix.commontest.endpoint3test;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.endpoint3.EndPoint;
import ch.nolix.common.endpoint3.IEndPointTaker;
import ch.nolix.common.endpoint3.NetEndPoint;
import ch.nolix.common.endpoint3.NetServer;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.test.Test;

//class
/**
 * A {@link NetEndPointTest} is a test for {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 * @month 2017-05-21
 * @lines 100
 */
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
		
		//setup
		final var netServer = new NetServer(port);
		netServer.addMainEndPointTaker(new EndPointTaker());
		
		//execution & verification
		expectRunning(
			() -> {
				final var netEndPoint = new NetEndPoint(port);
				Sequencer.waitForMilliseconds(500);
				netEndPoint.close();
			}
		)
		.doesNotThrowException();
		
		//cleanup
		netServer.close();
	}
	
	//method
	@TestCase
	public void testCase_sendAndGetReply() {
		
		//parameter definition
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		final var endPointTakerMock = new EndPointTaker();
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
