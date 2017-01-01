/*
 * file:	HTTPEndPointListener.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.common.endPoint;

//own import
import ch.nolix.common.constants.PortManager;

//class
/**
 * A HTTP alpha end point listener listens to alpha end points on the HTTP port.
 */
public final class HTTPEndPointListener extends EndPointListener {
	
	//constructor
	/**
	 * Creates new HTTP end point listener with the given end point taker.
	 * The new HTTP end point listener will start automatically.
	 * 
	 * @param endPointTaker
	 * @throws Exception if the given end point taker is null.
	 */
	public HTTPEndPointListener(IEndPointTaker endPointTaker)  {
		
		//Calls constructor of the base class.
		super(PortManager.HTTP_PORT, endPointTaker);
	}
}
