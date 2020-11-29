//package declaration
package ch.nolix.commontest.endpointtest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.communicationapi.IReceiver;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.endpoint.NetEndPoint;
import ch.nolix.common.endpoint.NetServer;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.test.Test;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link NetEndPointTest} is a test for {@link NetEndPoint}s.
 * 
 * @author Silvan Wyss
 * @date 2017-05-20
 * @lines 80
 */
public final class NetEndPointTest extends Test {
	
	//nested class
	private static final class ReceiverMock implements IReceiver {
		
		//optional attribute
		private String lastReceivedMessage;
		
		//method
		public String getLastReceivedMessage() {
			
			if (!hasReceivedMessage()) {
				throw new InvalidArgumentException(this, "has not received a message");
			}
			
			return lastReceivedMessage;
		}
		
		//method
		public boolean hasReceivedMessage() {
			return (lastReceivedMessage != null);
		}
		
		//method
		@Override
		public void receive(final String message) {
			
			Validator.assertThat(message).thatIsNamed(VariableNameCatalogue.MESSAGE).isNotNull();
			
			lastReceivedMessage = message;
		}
	}
	
	//method
	@TestCase
	public void testCase_send() {
		
		//test parameters
		final var port = 50000;
		final var message = "Hello World!";
		
		//setup	
		final var receiverFake = new ReceiverMock();
		final var netServer = new NetServer(port, ep -> ep.setReceiver(receiverFake));
		final var testUnit = new NetEndPoint(port);
		
		//execution
		testUnit.send(message);
		Sequencer.waitForMilliseconds(500);
		
		//verification
		expect(receiverFake.getLastReceivedMessage()).isEqualTo(message);
		
		//cleanup
		netServer.close();
		testUnit.close();
	}
}
