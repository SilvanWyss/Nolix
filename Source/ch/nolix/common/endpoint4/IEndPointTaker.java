/*
 * file:	IAlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	20
 */

//package declaration
package ch.nolix.common.endpoint4;

//own import
import ch.nolix.common.attributeapi.Named;

//interface
/**
 * A alpha end point taker takes alpha end points.
 * 
 * @param <M> The type of the messages a {@link IEndPointTaker} can send.
 * @param <R> The type of the replies a {@link IEndPointTaker} can receive.
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
