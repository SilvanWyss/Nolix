//package declaration
package ch.nolix.coreapi.programcontrolapi.resourcecontrolapi;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-07-05
 */
public interface ICloseController {
	
	//method declaration
	/**
	 * Closes all elements of the current {@link ICloseController}.
	 */
	void closeAll();
	
	//method declaration
	/**
	 * Adds the given element to the current {@link ICloseController}.
	 * 
	 * @param element
	 * @throws RuntimeException if the current {@link ICloseController} is already closed.
	 * @throws RuntimeException if the current {@link ICloseController} contains already the given element.
	 */
	void createCloseDependencyTo(GroupCloseable element);
	
	//method declaration
	/**
	 * @return the elements of the current {@link ICloseController}.
	 */
	IContainer<GroupCloseable> getRefElements();
	
	//method declaration
	/**
	 * @return true if the current {@link ICloseController} has closed its elements.
	 */
	boolean hasClosed();
}
