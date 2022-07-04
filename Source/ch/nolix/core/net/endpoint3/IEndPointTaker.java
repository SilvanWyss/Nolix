//package declaration
package ch.nolix.core.net.endpoint3;

import ch.nolix.coreapi.attributeuniversalapi.mandatoryattributeuniversalapi.Named;

//interface
/**
 * A duplex controller taker can take duplex controllers.
 * 
 * @author Silvan Wyss
 * @date 2016-11-01
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
