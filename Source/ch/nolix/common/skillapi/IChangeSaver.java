//package declaration
package ch.nolix.common.skillapi;

//own imports
import ch.nolix.common.requestapi.ChangeRequestable;

//interface
/**
 * A {@link IChangeSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @date 2018-04-9
 * @lines 20
 */
public interface IChangeSaver extends ChangeRequestable, Resettable {
	
	//method declaration
	/**
	 * Saves the changes of the current {@link IChangeSaver}.
	 */
	void saveChanges();
}
