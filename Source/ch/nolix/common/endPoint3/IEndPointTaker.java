/*
 * file:	IAlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	20
 */

//package declaration
package ch.nolix.common.endPoint3;

//own imports
import ch.nolix.common.attributeAPI.Named;

//interface
/**
 * A alpha end point taker takes alpha end points.
 */
public interface IEndPointTaker extends Named {

	//method declaration
	/**
	 * Takes the given alpha end point.
	 * 
	 * @param endPoint
	 */
	public abstract void takeEndPoint(EndPoint endPoint);
}
