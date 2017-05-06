/*
 * file:	HTTPEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	20
 */

//package declaration
package ch.nolix.system.modules;

//own imports
import ch.nolix.common.endPoint2.IEndPointTaker;
import ch.nolix.common.endPoint2.NetEndPoint;

//class
final class ApplicationManagerHTTPEndPointTaker implements IEndPointTaker {
	
	//method
	/**
	 * Takes the given end point.
	 * @param endPoint
	 */
	public final void takeEndPoint(NetEndPoint endPoint) {
		endPoint.setReceiver(new ApplicationManagerHTTPEndPointReceiver(endPoint));
	}
}
