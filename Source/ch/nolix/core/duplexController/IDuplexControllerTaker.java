/*
 * file:	IDuplexControllerTaker.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	20
 */

//package declaration
package ch.nolix.core.duplexController;

import ch.nolix.core.interfaces.Named;

//interface
public interface IDuplexControllerTaker extends Named {

	//abstract method
	/**
	 * Lets this duplex controller taker take the given duplex controller.
	 * 
	 * @param duplexController
	 */
	public void takeDuplexController(DuplexController duplexController);
}
