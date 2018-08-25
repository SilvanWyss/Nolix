//package declaration
package ch.nolix.core.endPoint2;

import ch.nolix.core.skillInterfaces.Named;

//interface
/**
 * An end point taker takes end points.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface IEndPointTaker extends Named {
	
	//default name
	public static final String DEFAULT_NAME = "Default";

	//abstract method
	/**
	 * Lets this end point taker take the given end point.
	 * 
	 * @param endPoint
	 */
	public abstract void takeEndPoint(EndPoint endPoint);
}
