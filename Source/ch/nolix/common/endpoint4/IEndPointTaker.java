/*
 * file:	IAlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	20
 */

//package declaration
package ch.nolix.common.endpoint4;

//own imports
import ch.nolix.common.attributeapi.Named;

//interface
/**
 * A alpha end point taker takes alpha end points.
 */
public interface IEndPointTaker<M, R> extends Named {

	//method declaration
	/**
	 * Takes the given alpha end point.
	 * 
	 * @param endPoint
	 */
	void takeEndPoint(EndPoint<M, R> endPoint);
}
