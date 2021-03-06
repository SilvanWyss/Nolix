/*
 * file:	IAlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	20
 */

//package declaration
package ch.nolix.common.net.endpoint2;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;

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
	void takeEndPoint(EndPoint endPoint);
}
