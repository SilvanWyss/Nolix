//package declaration
package ch.nolix.common.endPoint;

//interface
/**
 * An end point taker takes end points.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 */
public interface IEndPointTaker {

	//abstract method
	/**
	 * Lets this end point taker take the given end point.
	 * 
	 * @param endPoint
	 */
	public abstract void takeEndPoint(EndPoint endPoint);
}
