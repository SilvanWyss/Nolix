//package declaration
package ch.nolix.common.net.endpoint3;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;

//interface
/**
 * A duplex controller taker can take duplex controllers.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 20
 */
public interface IEndPointTaker extends Named {

	//method declaration
	/**
	 * Lets this duplex controller taker take the given duplex controller.
	 * 
	 * @param endPoint
	 */
	void takeEndPoint(EndPoint endPoint);
}
