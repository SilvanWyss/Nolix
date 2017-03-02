/*
 * file:	HTTPAlphaEndPointListener.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	30
 */

//package declaration
package ch.nolix.common.zetaEndPoint;

//own import
import ch.nolix.common.constants.PortManager;

//class
/**
 * A HTTP alpha end point listener listens to alpha end points on the HTTP port.
 */
public final class HTTPZetaEndPointListener extends ZetaEndPointListener {
	
	//constructor
	/**
	 * Creates new HTTP alpha end point listener with the given alpha end point taker.
	 * The new HTTP alpha end point listener will start automatically.
	 * 
	 * @param alphaEndPointTaker
	 * @throws Exception if the given alpha end point taker is null
	 */
	public HTTPZetaEndPointListener(IZetaEndPointTaker endPointTaker)  {
		
		//Calls constructor of the base class.
		super(PortManager.HTTP_PORT, endPointTaker);
	}
}
