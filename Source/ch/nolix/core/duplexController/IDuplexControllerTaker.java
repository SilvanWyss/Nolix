//package declaration
package ch.nolix.core.duplexController;

import ch.nolix.core.skillInterfaces.Named;

//interface
/**
 * A duplex controller taker can take duplex controllers.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 20
 */
public interface IDuplexControllerTaker extends Named {

	//abstract method
	/**
	 * Lets this duplex controller taker take the given duplex controller.
	 * 
	 * @param duplexController
	 */
	public abstract void takeDuplexController(DuplexController duplexController);
}
