//package declaration
package ch.nolix.coreapi.programcontrolapi.resourcecontrolapi;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;

//interface
public interface ICloseController {
	
	//method declaration
	/**
	 * Closes all close close dependencies of the current {@link ICloseController}.
	 */
	void closeAll();
	
	//method declaration
	/**
	 * Creates a close dependency between the elements of the current {@link ICloseController} and the given element.
	 * 
	 * @param element
	 * @throws RuntimeException if the current {@link ICloseController} is already closed.
	 * @throws RuntimeException if
	 * there exists already a close dependency between
	 * the elements of the current {@link ICloseController} and the given element.
	 */
	void createCloseDependencyTo(GroupCloseable element);
}
