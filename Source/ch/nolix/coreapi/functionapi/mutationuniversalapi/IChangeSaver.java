//package declaration
package ch.nolix.coreapi.functionapi.mutationuniversalapi;

//own imports
import ch.nolix.coreapi.functionapi.requestuniversalapi.ChangeRequestable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.Closeable;

//interface
/**
 * A {@link IChangeSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @date 2018-04-09
 */
public interface IChangeSaver extends ChangeRequestable, Closeable {
	
	//method declaration
	/**
	 * Saves the changes of the current {@link IChangeSaver}.
	 */
	void saveChanges();
}
