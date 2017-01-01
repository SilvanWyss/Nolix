/*
 * file:	IEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	20
 */

//package declaration
package ch.nolix.common.endPoint;

//interface
/**
 * An end point taker takes end points.
 */
public interface IEndPointTaker {

	//abstract method
	/**
	 * Takes the given end point.
	 * 
	 * @param endPoint
	 */
	public abstract void takeEndPoint(EndPoint endPoint);
}
