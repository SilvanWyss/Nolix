/*
 * file:	EndPointTaking.java
 * author:	Silvan Wyss
 * month:	2016-03
 * lines:	40
 */

//package declaration
package ch.nolix.common.endPoint;

//own import
import ch.nolix.common.util.Validator;

//package-visible class
/**
* A alpha end point taking takes a alpha end point asynchronously.
*/
final class EndPointTaking extends Thread {

	//attributes
	private final IEndPointTaker endPointTaker;
	private final EndPoint endPoint;
	
	//constructor
	/**
	 * Creates new end point taking with the given end point taker and end point.
	 * The new end point taking will start automatically.
	 * 
	 * @param endPointTaker
	 * @param endPoint
	 * @throws Exception if:
	 *  -the given end point taker is null
	 *  -the given end point is null
	 */
	public EndPointTaking(
		IEndPointTaker endPointTaker,
		EndPoint endPoint
	) {
		
		Validator.throwExceptionIfValueIsNull("end point taker", endPointTaker);
		Validator.throwExceptionIfValueIsNull("end point", endPoint);
		
		this.endPointTaker = endPointTaker;
		this.endPoint = endPoint;
		
		start();
	}
	
	//method
	/**
	 * Runs this end point taking.
	 */
	public final void run() {
		endPointTaker.takeEndPoint(endPoint);
	}
}
