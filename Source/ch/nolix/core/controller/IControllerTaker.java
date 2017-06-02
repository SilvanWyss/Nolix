/*
 * file:	IDuplexControllerTaker.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	20
 */

//package declaration
package ch.nolix.core.controller;

//interface
public interface IControllerTaker {

	//abstract method
	/**
	 * Lets this duplex controller taker take the given duplex controller.
	 * 
	 * @param controller
	 */
	public void takeDuplexController(Controller controller);
}
