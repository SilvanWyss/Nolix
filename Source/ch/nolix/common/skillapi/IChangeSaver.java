//package declaration
package ch.nolix.common.skillapi;

//own imports
import ch.nolix.common.requestapi.ChangeRequestable;

//interface
/**
 * A {@link IChangeSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @date 2018-04-09
 */
public interface IChangeSaver extends ChangeRequestable {
	
	//method declaration
	/**
	 * Saves the changes of the current {@link IChangeSaver}.
	 */
	void saveChanges();
}
