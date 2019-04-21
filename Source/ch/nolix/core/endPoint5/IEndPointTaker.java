//package declaration
package ch.nolix.core.endPoint5;

import ch.nolix.core.attributeAPI.Named;

//interface
/**
 * A duplex controller taker can take duplex controllers.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 20
 */
public interface IEndPointTaker extends Named {

	//abstract method
	/**
	 * Lets this duplex controller taker take the given duplex controller.
	 * 
	 * @param endPoint
	 */
	public abstract void takeDuplexController(EndPoint endPoint);
}
