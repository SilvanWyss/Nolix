//package declaration
package ch.nolix.core.endPoint2;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//own imports


import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * A net end point listeners listens to the incoming messages of a net end point
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
final class NetEndPointSubListener extends Thread {

	//attribute
	private final NetEndPoint netEndPoint;
	
	//constructor
	/**
	 * Creates new net end point listener that belongs to the given net end point.
	 * The net end point listener will start automatically.
	 * 
	 * @param netEndPoint
	 * @throws NullArgumentException if the givne net end point is null.
	 */
	public NetEndPointSubListener(NetEndPoint netEndPoint) {
		
		//Checks if the given net end point is not null.
		Validator.supposeThat(netEndPoint).thatIsInstanceOf(NetEndPoint.class).isNotNull();
		
		//Sets the net end point of this net end point listener.
		this.netEndPoint = netEndPoint;
		
		start();
	}

	//method
	/**
	 * Lets this net end point listener listens to incoming messages.
	 * Stops the net end point of this net end point listener when an error occurs.
	 */
	public void run() {
		try {
			
			final BufferedReader bufferedReader
			= new BufferedReader(new InputStreamReader(netEndPoint.getRefSocket().getInputStream()));
			
			while (netEndPoint.isNotAborted()) {
				final String line = bufferedReader.readLine();
				Sequencer.runInBackground(() -> netEndPoint.receive(line));
			}
		}
		catch (final IOException exception) {
			netEndPoint.abort(exception.getMessage());
		}
	}
}
