//package declaration
package ch.nolix.common.skillapi;

//own import
import ch.nolix.common.requestapi.ChangeRequestable;

//interface
/**
 * A {@link IChangesSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @date 2018-04-9
 * @lines 20
 * @param <CS> is the type of a {@link IChangesSaver}.
 */
public interface IChangesSaver<CS extends IChangesSaver<CS>> extends ChangeRequestable, Resettable {
	
	//method declaration
	/**
	 * Saves the changes of the current {@link IChangesSaver}.
	 */
	void saveChanges();
}
