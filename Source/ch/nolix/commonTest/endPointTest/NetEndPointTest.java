//package declaration
package ch.nolix.commonTest.endPointTest;

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
 * This class is a test class for the {@link NetEndPoint} class.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 90
 */
public final class NetEndPointTest extends Test {

	//method
	@TestCase
	public void testCase_send() {
		
		//test parameters
		final var port = 50000;
		final String message = "Hello World!";
		
		//setup	
			final ReceiverFake receiverFake = new ReceiverFake();
		
			final NetServer netServer = new NetServer(port, ep -> ep.setReceiver(receiverFake));
			
			final NetEndPoint netEndPoint = new NetEndPoint(port);
			
		//execution			
		netEndPoint.send(message);
		Sequencer.waitForMilliseconds(500);
		
		//verification
		expect(receiverFake.getLastReceivedMessage()).isEqualTo(message);
		
		//cleanup
		netServer.close();
		netEndPoint.close();
	}
	
	//inner class
	private final class ReceiverFake implements IReceiver {
		
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
}
