//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.nolix.common.containers.List;
import ch.nolix.common.logger.Logger;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//package-visible class
/**
 * A net end point sub listener listens to the incoming messages of a net end point.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
final class NetEndPointSubListener extends Thread {

	//attribute
	private final NetEndPoint netEndPoint;
	
	//constructor
	/**
	 * Creates a new net end point sub listener that belongs to the given net end point.
	 * 
	 * @param netEndPoint
	 * @throws ArgumentIsNullException if the given net end point is null.
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
			var lines = new List<String>();
			while (netEndPoint.isOpen()) {
				
				final var line = bufferedReader.readLine();
				
				if (line == null) {
					netEndPoint.close();
					break;
				}
				
				if (line.isEmpty()) {
					if (lines.isEmpty()) {
						Logger.logWarning("NetServer received unneccessary empty line.");
					}
					else {					
						final var lines2 = lines;
						Sequencer.runInBackground(() -> netEndPoint.receiveRawMessages(lines2));
						lines = new List<String>();
					}
				}
				else {
					lines.addAtEnd(line);
				}
			}
		}
		catch (final IOException exception) {
			netEndPoint.close();
		}
	}
}
