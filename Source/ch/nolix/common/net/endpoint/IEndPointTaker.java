//package declaration
package ch.nolix.common.net.endpoint;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;

//interface
/**
 * An end point taker takes end points.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface IEndPointTaker extends Named {
	
	//method declaration
	/**
	 * Lets this end point taker take the given end point.
	 * 
	 * @param endPoint
	 */
	void takeEndPoint(EndPoint endPoint);
}
