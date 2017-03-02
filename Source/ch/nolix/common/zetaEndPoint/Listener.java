/*
 * file:	Listener.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	60
 */

//package declaration
package ch.nolix.common.zetaEndPoint;

//Java imports
import java.io.BufferedReader;
import java.io.InputStreamReader;

//own imports
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.util.Validator;

//package-visible class
/**
 * A Listener listens to the incoming strings of a socket that belongs to a alpha end point and passes the strings to the alpha end point.
 */
final class Listener extends Thread {

	//attribute
	private final ZetaEndPoint alphaEndPoint;
	
	//constructor
	/**
	 * Creates and starts new listener that belongs to the given alpha end point.
	 * The new listener will start automatically.
	 * 
	 * @param alphaEndPoint
	 * @throws Exception if the given alpha end point is null.
	 */
	public Listener(ZetaEndPoint alphaEndPoint) {
		
		Validator.throwExceptionIfValueIsNull("alpha end point", alphaEndPoint);
		
		this.alphaEndPoint = alphaEndPoint;
		start();
	}

	//method
	/**
	 * Listens to incoming strings.
	 * Stops the alpha end point this listener belongs to when an error occurs.
	 */
	public void run() {
		try {
			
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(alphaEndPoint.getRefSocket().getInputStream()));
			
			while (alphaEndPoint.isRunning()) {	
				final String line = bufferedReader.readLine();
				Sequencer.runInBackground(()->alphaEndPoint.receive(line));
			}
		}
		catch (Exception e) {
			alphaEndPoint.abort(e.getMessage());
		}
	}
}