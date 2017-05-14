/*
 * file:	ILevel2Controller.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	40
 */

//package declaration
package ch.nolix.core.controller;

//own import
import ch.nolix.core.specification.Statement;

//interface
/**
 * A level 2 controller is a level 1 controller that can also return data.
 * 
 * The default methods of this interface need not to be overwritten.
 */
public interface ILevel2Controller extends ILevel1Controller {
		
	//default method
	/**
	 * @param request
	 * @return the data the given request requests
	 */
	@SuppressWarnings("unchecked")
	public default <D> D getData(Statement request) {
		return (D)getRawData(request);
	}
	
	//default method
	/**
	 * @param request
	 * @return the data the given request requests
	 */
	@SuppressWarnings("unchecked")
	public default <D> D getData(String request) {
		return (D)getRawData(new Statement(request));
	}
	
	//abstract method
	/**
	 * @param request
	 * @return the data the given request requests
	 */
	public abstract Object getRawData(Statement request);
}
