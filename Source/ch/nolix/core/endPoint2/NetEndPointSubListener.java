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
 * A net end point sub listener listens to the incoming messages of a net end point.
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
	 * Creates a new net end point sub listener that belongs to the given net end point.
	 * 
	 * @param netEndPoint
	 * @throws NullArgumentException if the given net end point is null.
	 */
	public NetEndPointSubListener(final NetEndPoint netEndPoint) {
		
		//Checks if the given net end point is not null.
		Validator.suppose(netEndPoint).isOfType(NetEndPoint.class);
		
		//Sets the net end point of this net end point sub listener.
		this.netEndPoint = netEndPoint;
		
		start();
	}
	
	//method
	/**
	 * Runs this net end point sub listener.
	 */
	@Override
	public void run() {
		try (
			final BufferedReader bufferedReader
			= new BufferedReader(new InputStreamReader(netEndPoint.getRefSocket().getInputStream()))
		) {
			while (netEndPoint.isAlive()) {
				
				final var line = bufferedReader.readLine();
				
				if (line == null) {
					netEndPoint.close();
					break;
				}
				
				Sequencer.runInBackground(() -> netEndPoint.receive(line));
			}
		}
		catch (final IOException exception) {
			netEndPoint.close();
		}
	}
}
