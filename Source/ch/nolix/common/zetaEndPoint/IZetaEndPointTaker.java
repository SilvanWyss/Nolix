/*
 * file:	IAlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	20
 */

//package declaration
package ch.nolix.common.zetaEndPoint;

import ch.nolix.common.interfaces.Named;

//interface
/**
 * A alpha end point taker takes alpha end points.
 */
public interface IZetaEndPointTaker extends Named {

	//abstract method
	/**
	 * Takes the given alpha end point.
	 * 
	 * @param alphaEndPoint
	 */
	public abstract void takeAlphaEndPoint(ZetaEndPoint alphaEndPoint);
}
