/*
 * file:	ILevel3Controller.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	40
 */

//package declaration
package ch.nolix.core.controllerInterfaces2;

//own import
import ch.nolix.core.specification.Statement;

//interface
/**
 * A level 3 controller is a level 2 controller that can also return references.
 * 
 * The default methods of this interface need not to be overwritten.
 */
public interface ILevel3Controller extends ILevel2Controller {
	
	//default method
	/**
	 * @param request
	 * @return the reference the given request requests
	 */
	@SuppressWarnings("unchecked")
	public default <D> D getReference(Statement request) {
		return (D)getRawReference(request);
	}

	//default method
	/**
	 * @param request
	 * @return the reference the given request requests
	 */
	@SuppressWarnings("unchecked")
	public default <D> D getReference(String request) {
		return (D)getRawReference(new Statement(request));
	}
	
	//abstract method
	/**
	 * @param request
	 * @return the reference the given request requests
	 */
	public abstract Object getRawReference(Statement request);
}
