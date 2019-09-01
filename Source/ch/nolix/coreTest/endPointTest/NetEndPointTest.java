//package declaration
package ch.nolix.coreTest.endPointTest;

import ch.nolix.common.communicationAPI.IReceiver;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.endPoint.NetEndPoint;
import ch.nolix.common.endPoint.NetServer;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.test.Test;
import ch.nolix.common.validator.Validator;

//test class
/**
 * This class is a test class for the {@link NetEndPoint} class.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 90
 */
public final class NetEndPointTest extends Test {

	//test case
	public void testCase_send() {
		
		//test parameters
		final int port = 50000;
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
			
			Validator
			.suppose(message)
			.thatIsNamed(VariableNameCatalogue.MESSAGE)
			.isNotNull();
			
			lastReceivedMessage = message;
		}
	}
}
