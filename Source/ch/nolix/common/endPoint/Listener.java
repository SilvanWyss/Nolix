/*
 * file:	Listener.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	60
 */

//package declaration
package ch.nolix.common.endPoint;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//own import
import ch.nolix.common.util.Validator;

//package-visible class
/**
 * A Listener listens to the incoming strings of a socket that belongs to an end point and passes the strings to the end point.
 */
final class Listener extends Thread {

	//attribute
	private final EndPoint endPoint;
	
	//constructor
	/**
	 * Creates new listener that belongs to the given end point.
	 * The new listener will start automatically.
	 * 
	 * @param endPoint
	 * @throws Exception if the given end point is null
	 */
	public Listener(EndPoint endPoint) {
		
		Validator.throwExceptionIfValueIsNull("end point", endPoint);
		
		this.endPoint = endPoint;
		start();
	}

	//method
	/**
	 * Listens to incoming strings.
	 * Stops the end point this listener belongs to when an error occurs.
	 */
	public void run() {
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(endPoint.getRefSocket().getInputStream()));
			
			while (endPoint.isRunning()) {
				endPoint.getRefReceiver().receive(bufferedReader.readLine());
			}
		}
		catch (IOException e) {
			endPoint.abort(e.getMessage());
		}
	}
}