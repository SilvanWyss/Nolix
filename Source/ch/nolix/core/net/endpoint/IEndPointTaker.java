//package declaration
package ch.nolix.core.net.endpoint;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Named;

//interface
/**
 * An end point taker takes end points.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
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
