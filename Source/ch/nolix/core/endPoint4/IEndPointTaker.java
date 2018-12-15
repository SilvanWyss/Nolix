/*
 * file:	IAlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	20
 */

//package declaration
package ch.nolix.core.endPoint4;

//own imports
import ch.nolix.core.skillAPI.Named;

//interface
/**
 * A alpha end point taker takes alpha end points.
 */
public interface IEndPointTaker<M, R> extends Named {

	//abstract method
	/**
	 * Takes the given alpha end point.
	 * 
	 * @param endPoint
	 */
	public abstract void takeEndPoint(EndPoint<M, R> endPoint);
}
