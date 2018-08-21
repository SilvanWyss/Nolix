//package declaration
package ch.nolix.coreTest.endPointTest;

//own imports
import ch.nolix.core.communicationInterfaces.IReceiver;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.endPoint.EndPoint;
import ch.nolix.core.endPoint.IEndPointTaker;
import ch.nolix.core.endPoint.NetServer;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.test2.Test;
import ch.nolix.primitive.validator2.Validator;
import ch.nolix.core.endPoint.NetEndPoint;

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
	public void test_send() {
		
		//test parameters
		final int port = 50000;
		final String message = "Hello World!";
		
		//setup	
			final ReceiverFake receiverFake = new ReceiverFake();
		
			final NetServer netServer
			= new NetServer(
				port,
				new IEndPointTaker() {					
					public void takeEndPoint(final EndPoint endPoint) {
						endPoint.setReceiver(receiverFake);
					}
				}
			);
			
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
				throw new InvalidStateException(this, "received no message");
			}
			
			return lastReceivedMessage;
		}
		
		//method
		public boolean hasReceivedMessage() {
			return (lastReceivedMessage != null);
		}
		
		//method
		public void receive(final String message) {
			
			Validator
			.suppose(message)
			.thatIsNamed(VariableNameCatalogue.MESSAGE)
			.isNotNull();
			
			lastReceivedMessage = message;
		}
	}
}
